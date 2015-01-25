
package com.mba;


import javax.microedition.lcdui.*;
/**
 *
 * @author VISHAL
 */
public class FormMenu implements CommandListener
{
    private Command ok5;// menu
    private Command back5;// menu
    private Command ok12;//Form Menu

    private List formhome;
    private List fmenu;

    private String menu="";
    private char cmenu[]=menu.toCharArray();

    private String category="Soaps,Detergent,Juice,Biscuit,Chips,Deodrant,Fruits;";

    private Form formbuyitem;
    private Form formcheckyourcart;


    public List makeFormMenu()
    {
        fmenu=new List("Your Menu:",List.IMPLICIT);
        back5=new Command("Back",Command.BACK,1);
        ok12=new Command("View Your Cart",Command.OK,1);
        System.out.println("your way flag is : "+FormHttpSms.getWayFlag());
        menu = category;
        cmenu=menu.toCharArray();
        returnMenu(fmenu,cmenu);
        fmenu.addCommand(back5);
        fmenu.addCommand(ok12);
        fmenu.setCommandListener(this);
        return fmenu;
    }

    static List getFormMenu()
    {
        FormMenu fm = new FormMenu();
        return fm.makeFormMenu();
    }

    public void returnMenu(List cg,char menu[])
    {
        String name="";
        int i;
        for(i=0;i<menu.length;i++)
        {

                    if(menu[i]!=',')
                    {
                        name=name+menu[i];
                    }
                    else
                    {
                        cg.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    private void showCart()
    {
        if(!(FormBuyItem.bill.equals("")))
        {
            System.out.println(FormBuyItem.bill);
            formcheckyourcart=FormCheckYourCart.getFormCheckYourCart();
            MobileMidlet.switchDisplay(formcheckyourcart);
            System.out.println("Choosed : Check Your Cart");
        }
        else
        {
            Alert alert= new Alert("SORRY ::","No Purchases yet! Please purchase Something",null,null);
            alert.setTimeout(Alert.FOREVER);
            alert.setType(AlertType.ERROR);
            MobileMidlet.switchDisplay(alert);
        }
    }



    public void commandAction(Command c, Displayable d)
    {
            if(c==List.SELECT_COMMAND)
            {
                formbuyitem=FormBuyItem.getFormBuyItem(fmenu.getString(fmenu.getSelectedIndex()));
                MobileMidlet.switchDisplay(formbuyitem);
                System.out.println("Selected Item:" + fmenu.getString(fmenu.getSelectedIndex()));
            }
            else if(c==ok12)
            {
                showCart();
            }
            else if(c==back5)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formhome);
            }
    }

}
