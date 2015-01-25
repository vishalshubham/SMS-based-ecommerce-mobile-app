
package com.mba;

import javax.microedition.lcdui.*;
/**
 *
 * @author VISHAL
 */
public class FormCheckYourCart implements CommandListener
{

    private ChoiceGroup billlist;
    private char[] cbill;
    private Command ok8;
    private Command ok9;
    private Command ok10;
    private Command back8;
    static String edititem;
    private Form formedititem;
    private Form formcheckyourcart;
    private List formhome;
    private String str;

    public Form makeFormCheckYourCart()
    {
        Form fcheckyourcart=new Form("You Have Purchased:");
        int total = 0;
        int itemtotal = 0;
        int indexattherate = 0;
        int indexdash = 0;

        billlist=new ChoiceGroup("Your Cart :", ChoiceGroup.EXCLUSIVE);
        cbill= FormBuyItem.bill.toCharArray();
        returnList(billlist, cbill);


        System.out.println(billlist.size());
        for(int i = 0 ; i < billlist.size() ; i++)
        {
            String units = "";
            String rate = "";
            char[] arrequal = billlist.getString(i).toCharArray();
            String strequal = billlist.getString(i);
            indexattherate = strequal.indexOf('@');
            indexdash = strequal.indexOf('-');
            for(int j = 0 ; j < indexdash ; j++)
            {
                units = units + arrequal[j];
            }
            for(int j = indexattherate + 1 ; j < billlist.getString(i).length() ; j++)
            {
                rate = rate + arrequal[j];
            }
            System.out.println(i + billlist.getString(i));

            itemtotal = Integer.parseInt(units) * Integer.parseInt(rate);
            total = total + itemtotal;
        }

        StringItem strtotal = new StringItem("Your Total is : ",Integer.toString(total));
        fcheckyourcart.append(new String("    Qty Item      Price"));
        fcheckyourcart.append(billlist);
        fcheckyourcart.append(strtotal);
        System.out.println("YOUR LIST IS : ");

        ok8=new Command("Edit Item",Command.OK,1);
        ok9=new Command("Delete Item",Command.OK,2);
        ok10=new Command("Send Cart",Command.OK,3);
        back8=new Command("Back",Command.BACK,1);
        fcheckyourcart.addCommand(ok8);
        fcheckyourcart.addCommand(ok9);
        fcheckyourcart.addCommand(ok10);
        fcheckyourcart.addCommand(back8);
        fcheckyourcart.setCommandListener(this);
        return fcheckyourcart;
    }


    static Form getFormCheckYourCart()
    {
        FormCheckYourCart fc = new FormCheckYourCart();
        return fc.makeFormCheckYourCart();
    }

    public void returnList(ChoiceGroup c,char citem[])
    {
        String name="";
        int i;
        for(i=0;i<citem.length;i++)
        {

                    if(citem[i]!=',')
                    {
                        name=name+citem[i];
                    }
                    else
                    {
                        c.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    private boolean sendCart()
    {
        DateGetter d = new DateGetter();
        String spacedate = d.getDate();
        FormBuyItem.bill = generateBill(FormBuyItem.bill);
//        str = "2-"+FormLogin.getUserName() +"."+FormLogin.getPassword()+"="+ FormBuyItem.bill + "*"+spacedate;
        str = "2!"+FormLogin.getUserName() +"$"+FormLogin.getPassword()+"("+ FormBuyItem.bill + ")"+spacedate;
         if(SendSMS.sendSms(str))
            {
                return true;
            }
            else
            {
                return false;
            }
    }

    public String generateBill(String bill)
    {
        char[] cbill = bill.toCharArray();
        String newbill = "";
        for(int i = 0; i < bill.length() ; i++)
        {
            if(cbill[i] != '.')
            {
                newbill = newbill + cbill[i];
            }
            else if(cbill[i] == '.')
            {
                while(cbill[i]!= '=')
                {
                    i++;
                }
            }
        }
        return newbill;
    }

    public void commandAction(Command c, Displayable d)
    {
         if(c==ok8)//Edit Item
            {
                edititem=billlist.getString(billlist.getSelectedIndex());

                formedititem=FormEditItem.getFormEditItem(edititem);
                MobileMidlet.switchDisplay(formedititem);
            }
            else if(c==ok9)//Delete Item
            {
                System.out.println("This is our list : "+FormBuyItem.bill);
                String deleteitem=billlist.getString(billlist.getSelectedIndex());
                FormBuyItem.bill=StringUtilities.replaceData(FormBuyItem.bill,deleteitem+",","");
                System.out.println("This is our list after deletion : "+FormBuyItem.bill);

                formcheckyourcart=FormCheckYourCart.getFormCheckYourCart();
                MobileMidlet.switchDisplay(formcheckyourcart);
            }
            else if(c==ok10)//Send Cart
            {
                if(sendCart())
                {
                        Alert alert= new Alert("THANKS YOU","Your cart has been registered and Delivery will be done till tommorow. And you can cancel the order within 12 hours from order time",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.INFO);
                        MobileMidlet.switchDisplay(alert);
                }
                else
                {
                        Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                }
            }
            else if(c==back8)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formhome);
            }
    }

}
