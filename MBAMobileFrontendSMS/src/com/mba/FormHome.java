
package com.mba;

import javax.microedition.lcdui.*;
/**
 *
 * @author VISHAL
 */
public class FormHome implements CommandListener
{

    private List fhome;
    private List formmenu;
    private List formchange;
    private String home="View Item/Place Order,Check Your Cart,Change Name,Change Address,Change Contact,Help,Exit,";
    private char chome[]=home.toCharArray();
    private Form formcheckyourcart;
    private Form formcancelorder;
    private Form formhelp;
    private Form formchangeany;

    public List makeFormHome()
    {
        fhome=new List("Enter Your Choice:",List.IMPLICIT);
        returnMenu(fhome,chome);
        fhome.setCommandListener(this);
        return fhome;
    }

    static List getFormHome()
    {
        FormHome fh = new FormHome();
        return fh.makeFormHome();
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
                if(fhome.getString(fhome.getSelectedIndex()).equals("View Item/Place Order"))
                {
                    formmenu=FormMenu.getFormMenu();                         // Form Item Menu Getter
                    MobileMidlet.switchDisplay(formmenu);
                    System.out.println("Choosed : View Item");
                }

                else if(fhome.getString(fhome.getSelectedIndex()).equals("Check Your Cart"))
                {
                    showCart();
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Change Name"))
                {
                    formchangeany = FormChangeAny.getFormChangename();
                    MobileMidlet.switchDisplay(formchangeany);
                    System.out.println("Choosed : change name");
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Change Address"))
                {
                    formchangeany = FormChangeAny.getFormChangeAddress();
                    MobileMidlet.switchDisplay(formchangeany);
                    System.out.println("Choosed : change address");
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Change Contact"))
                {
                    formchangeany = FormChangeAny.getFormChangeContact();
                    MobileMidlet.switchDisplay(formchangeany);
                    System.out.println("Choosed : change contact");
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Change Password"))
                {
                    formchangeany = FormChangeAny.getFormChangePassword();
                    MobileMidlet.switchDisplay(formchangeany);
                    System.out.println("Choosed : change password");
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Help"))
                {
                    formhelp=FormHelp.getFormHelp();                         // Form Item Menu Getter
                    MobileMidlet.switchDisplay(formhelp);
                    System.out.println("Choosed : Help");
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Exit"))
                {
                    System.out.println("Choosed : Exit");
                }
            }
    }

}
