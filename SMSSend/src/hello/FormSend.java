/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author VISHAL
 */
public class FormSend implements CommandListener
{
    	Command send, exit;
	TextField phone, message;
	StringItem smsResult;
	Form form, formresult;

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


        form.addCommand(send = new Command("Send", Command.OK, 1));
        form.addCommand(exit = new Command("Exit", Command.EXIT, 1));
        form.setCommandListener(this);
        return form;
    }

    public void commandAction(Command c, Displayable d)
    {
        if(c == send)
        {
            boolean result = sendSms(phone.getString(), message.getString());
            if(result == true)
            {
                formresult = FormResult.getFormResult();
            }
        }
        else if(c == exit)
        {
            MobileMidlet m = new MobileMidlet();
            m.notifyDestroyed();
        }
    }


    public boolean sendSms(String number, String message){
		boolean result = true;
		try {
			//sets address to send message
			String addr = "sms://"+number;
			// opens connection
			MessageConnection conn = (MessageConnection) Connector.open(addr);
			// prepares text message
			TextMessage msg =
				(TextMessage)conn.newMessage(MessageConnection.TEXT_MESSAGE);
			//set text
			msg.setPayloadText(message);
			// send message
			conn.send(msg);
			conn.close();
		}
		catch(SecurityException se)
		{
			result = false;
		}
		catch (Exception e)
		{
			result = false;
		}
		return result;
	}
}
