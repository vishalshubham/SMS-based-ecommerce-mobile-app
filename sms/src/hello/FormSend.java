/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author VISHAL
 */
public class FormSend implements CommandListener
{
    	Command send, exit;
	TextField phone, message;
	StringItem smsResult;
	Form form;

    static Form getFormSend()
    {

        FormSend fb = new FormSend();
        return fb.makeFormSend();

        
    }

    public Form makeFormSend()
    {
        System.out.println("hi");
        form = new Form("SMS Test");

        phone = new TextField("Phone number", "", 255, TextField.PHONENUMBER);

        form.append(phone);

        message = new TextField("Message", "", 160, TextField.ANY);

        form.append(message);

        smsResult = new StringItem("SMS result", "");

        form.append(smsResult);

        form.addCommand(send = new Command("Send", Command.OK, 1));
        form.addCommand(exit = new Command("Exit", Command.EXIT, 1));
        form.setCommandListener(this);
        return form;
    }

    public void commandAction(Command c, Displayable d)
    {
        
    }

}
