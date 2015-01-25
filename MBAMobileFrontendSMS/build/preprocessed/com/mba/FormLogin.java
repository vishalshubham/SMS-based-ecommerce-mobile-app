
package com.mba;

import javax.microedition.lcdui.*;

/**
 *
 * @author VISHAL
 */
public class FormLogin implements CommandListener
{
    private TextField tlog;
    private TextField tpass;

    private Command ok;
    private Command back;

    static String strusername;
    static String strPassword;

    private Form flogin;
    private List formhttpsms;
    private List formhome;

    public Form makeFormLogin()
    {
        flogin=new Form("Enter Your Account:");
        tlog=new TextField("Username :",null,30,TextField.ANY);
        tpass=new TextField("Password :",null,15,TextField.ANY|TextField.PASSWORD);
        ok=new Command("Login",Command.OK,1);
        back=new Command("Exit",Command.EXIT,2);
        flogin.append(tlog);
        flogin.append(tpass);
        flogin.addCommand(ok);
        flogin.addCommand(back);
        flogin.setCommandListener(this);
        return flogin;
    }


    static Form getFormLogin()
    {
        FormLogin fs = new FormLogin();
        return fs.makeFormLogin();
    }

    static String getUserName()
    {
        return strusername;
    }

    static String getPassword()
    {
        return strPassword;
    }
    public void commandAction(Command c, Displayable d)
    {
        if(d==flogin)
        {
            if(c==ok)
            {
                if(FormHttpSms.wayflag == 2)
                {
                    //if(tlog.getString().equals("best.mart.2011@gmail.com") && tpass.getString().equals("bestmart"))
                    if(tlog.getString().length() > 7 && tpass.getString().length() > 7)
                    {
                        formhome = FormHome.getFormHome();
                        MobileMidlet.switchDisplay(formhome);
                        strusername = tpass.getString();
                        strPassword = tlog.getString();
                    }
                    else
                    {
                        tpass.setString("");
                        Alert alert= new Alert("ERROR","Wrong username or Password",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                    }
                }
            }
            else if(c==back)
            {
                formhttpsms = FormHttpSms.getFormHttpSms();
                MobileMidlet.switchDisplay(formhttpsms);
            }
        }
    }

}
