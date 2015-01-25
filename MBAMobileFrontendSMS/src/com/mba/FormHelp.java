/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
/**
 *
 * @author VISHAL
 */
public class FormHelp implements CommandListener
{
    private Form fgeneralquery;   
    private TextField tquery;
    private Command ok1;
    private Command back1;
    private List formhelp;

   
    static Form getFormHelp()
    {
        FormHelp fc = new FormHelp();
        return fc.makeFormHelp();
    }

    private Form makeFormHelp()
    {
        fgeneralquery=new Form("Write Your Query :");
        tquery=new TextField("Query:",null,500,TextField.ANY);
        fgeneralquery.append(tquery);
        ok1=new Command("Send",Command.OK,1);
        back1=new Command("Back",Command.BACK,1);
        fgeneralquery.addCommand(ok1);
        fgeneralquery.addCommand(back1);
        fgeneralquery.setCommandListener(this);
        return fgeneralquery;
    }


     public void commandAction(Command c, Displayable d)
    {
        if(c==back1)
        {
            tquery.setString("");
            formhelp = FormHome.getFormHome();
            MobileMidlet.switchDisplay(formhelp);
        }
        else if(c==ok1)
        {
            String msg = tquery.getString();
            msg = processMsg(msg);
            if(sendGeneralMail(msg))
                {
                    Alert alert= new Alert("THANKS","Your request has been processed.",null,null);
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
    }




    private boolean sendGeneralMail(String msg)
    {
        DateGetter d = new DateGetter();
        String date = d.getDate();
        msg =  "8!"+FormLogin.getUserName()+"$"+FormLogin.getPassword()+"(" + msg + ")" + date;
        if(SendSMS.sendSms(msg))
            {
                return true;
            }
            else
            {
                return false;
            }
    }

    private String processMsg(String msg)
    {
        System.out.println("this is my message : "+msg);
        while(msg.indexOf(" ")!=-1)
        {
            msg = StringUtilities.replaceData(msg," ","%20");
            System.out.println("My message : "+msg);
        }
        return msg;
    }
}
