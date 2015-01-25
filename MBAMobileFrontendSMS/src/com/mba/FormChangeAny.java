
package com.mba;


import java.io.DataInputStream;
import javax.microedition.lcdui.*;
/**
 *
 * @author VISHAL
 */
public class FormChangeAny implements CommandListener
{
    private Form fchangename,fchangeaddress,fchangecontact,fchangepassword;
    private TextField tname;
    private Command ok1,back1;
    private TextField taddress1;
    private TextField taddress2;
    private TextField tcity;
    private TextField tstate;
    private TextField tpincode;
    private Command ok2;
    private Command back2;
    private TextField tmobile;
    private TextField temail;
    private Command ok3;
    private Command back3;
    private TextField toldpass;
    private TextField tnewpass;
    private TextField tconfirmnewpass;
    private Command ok4;
    private Command back4;
    private List formchange;
    private String strusername;

    public Form makeFormChangeName()
    {
        fchangename=new Form("Change Your Name:");
        tname=new TextField("New Name:",null,25,TextField.ANY);
        fchangename.append(tname);
        ok1=new Command("Change",Command.OK,1);
        back1=new Command("Back",Command.BACK,1);
        fchangename.addCommand(ok1);
        fchangename.addCommand(back1);
        fchangename.setCommandListener(this);
        return fchangename;
    }

    static Form getFormChangename()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangeName();
    }

     public Form makeFormChangeAddress()
    {
        fchangeaddress=new Form("Change Your Address:");
        taddress1=new TextField("ADDRESS LINE 1:",null,40,TextField.ANY);
        taddress2=new TextField("ADDRESS LINE 2:",null,40,TextField.ANY);
        tcity=new TextField("CITY:",null,40,TextField.ANY);
        tstate=new TextField("STATE:",null,40,TextField.ANY);
        tpincode=new TextField("PIN CODE:",null,40,TextField.NUMERIC);

        ok2=new Command("Change",Command.OK,1);
        back2=new Command("Back",Command.BACK,1);
        fchangeaddress.addCommand(ok2);
        fchangeaddress.addCommand(back2);
        fchangeaddress.setCommandListener(this);

        fchangeaddress.append(taddress1);
        fchangeaddress.append(taddress2);
        fchangeaddress.append(tcity);
        fchangeaddress.append(tstate);
        fchangeaddress.append(tpincode);

        return fchangeaddress;
    }

    static Form getFormChangeAddress()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangeAddress();
    }

     public Form makeFormChangeContact()
    {
        fchangecontact=new Form("Change Your Contact:");
        tmobile=new TextField("New Mobile Number:",null,40,TextField.PHONENUMBER);
        temail=new TextField("New E-mail Address:",null,40,TextField.ANY);
        fchangecontact.append(tmobile);
        fchangecontact.append(temail);
        ok3=new Command("Change",Command.OK,1);
        back3=new Command("Back",Command.BACK,1);
        fchangecontact.addCommand(ok3);
        fchangecontact.addCommand(back3);
        fchangecontact.setCommandListener(this);

        return fchangecontact;
    }

    static Form getFormChangeContact()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangeContact();
    }

     public Form makeFormChangePassword()
    {
        System.out.println("ddddddddddddddddddddddddddddddddddddd");
        fchangepassword=new Form("Change Your Password:");
        toldpass= new TextField("Old Password:",null,20,TextField.ANY|TextField.PASSWORD);
        tnewpass= new TextField("New Password:",null,20,TextField.ANY|TextField.PASSWORD);
        tconfirmnewpass= new TextField("Confirm New Password:",null,20,TextField.ANY|TextField.PASSWORD);
        fchangepassword.append(toldpass);
        fchangepassword.append(tnewpass);
        fchangepassword.append(tconfirmnewpass);
        ok4=new Command("Change",Command.OK,1);
        back4=new Command("Back",Command.BACK,1);
        fchangepassword.addCommand(ok4);
        fchangepassword.addCommand(back4);
        fchangepassword.setCommandListener(this);

        return fchangepassword;
    }

    static Form getFormChangePassword()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangePassword();
    }

    private boolean changeContact()
    {
            DateGetter d = new DateGetter();
            String date = d.getDate();
            String contact= tmobile.getString();
            String email= temail.getString();
            String str = "5!"+FormLogin.getUserName() +"$"+FormLogin.getPassword()+"("  + contact + ";" + email + ")"+date;
            if(SendSMS.sendSms(str))
            {
                return true;
            }
            else
            {
                return false;
            }
    }

    private boolean changeAddress()
    {
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            DataInputStream dis;
            String address= taddress1.getString()+","+taddress2.getString();
            String city= tcity.getString();
            String state= tstate.getString();
            String pincode= tpincode.getString();
                String str = "4!"+FormLogin.getUserName() +"$"+FormLogin.getPassword() +"("+ address+";"+city+":"+state+"%"+pincode+")"+spacedate;
                if(SendSMS.sendSms(str))
            {
                return true;
            }
            else
            {
                return false;
            }
    }

    private boolean changeName()
    {
        try
        {
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            System.out.print("Changing Name to: " + tname.getString());
            String str =  "3!"+FormLogin.getUserName()+ "$"+ FormLogin.getPassword()+"("+ tname.getString() + ")" + spacedate;

            if(SendSMS.sendSms(str))
            {
                return true;
            }
            else
            {
                return false;
            }
            
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }


     public boolean checkNumeric(String str)
     {
         char[] arrstr = str.toCharArray();
         for(int i = 0 ; i < str.length() ; i++)
         {
             if(arrstr[i] == '0' || arrstr[i] == '1' || arrstr[i] == '2' || arrstr[i] == '3' || arrstr[i] == '4' || arrstr[i] == '5' || arrstr[i] == '6' || arrstr[i] == '7' || arrstr[i] == '8' || arrstr[i] == '9')
             return false;
         }
         return true;
     }

    public void commandAction(Command c, Displayable d)
    {
        if(d==fchangename)
        {
            if(c==back1)
            {
                tname.setString("");
                formchange = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formchange);
            }
            else if(c==ok1)
            {
                if(tname.getString().length() >5 & checkNumeric(tname.getString()))
                {
                    if(changeName())
                    {
                        Alert alert= new Alert("NAME CHANGED::","Your Change Name Request Has Been Sent To the server :: New Name : "+tname.getString() + " . After Some time You will recieve a confirmation SMS, if not please contact operator immediately",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.INFO);
                        MobileMidlet.switchDisplay(alert);
                    }
                    else
                    {
                        Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server or You do not have enough balance to send SMS.",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                    }
                     }
                else
                    {
                        Alert alert= new Alert("SORRY::","Please Enter a valid name"+tname.getString(),null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                    }
            }
        }
        else if(d==fchangeaddress)
        {
            if(c==back2)
            {
                taddress1.setString("");
                taddress2.setString("");
                tcity.setString("");
                tstate.setString("");
                tpincode.setString("");
                formchange = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formchange);
            }
            else if(c==ok2)
            {
                if(taddress1.getString().length() >5 & taddress2.getString().length() >5 & tcity.getString().length() >2 & tstate.getString().length() >2 & tpincode.getString().length() >5)
                {
                if(changeAddress())
                {
                    Alert alert= new Alert("ADDRESS CHANGED::","Your Account Address Has Been Changed To::"+tcity.getString()+"::"+tstate.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet.switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet.switchDisplay(alert);
                }
                }
                else
                    {
                        Alert alert= new Alert("SORRY::","Please Enter a valid Address",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                    }
            }
        }
        else if(d==fchangecontact)
        {
            if(c==back3)
            {
                tmobile.setString("");
                temail.setString("");
                formchange = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formchange);
            }
            else if(c==ok3)
            {
                if(temail.getString().length() >7 & tmobile.getString().length() >13)
                {
                if(changeContact())
                {
                    Alert alert= new Alert("CONTACT CHANGED::","Your Account Contact Has Been Changed To::"+tmobile.getString()+"::"+temail.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet.switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet.switchDisplay(alert);
                }
                }
                else
                    {
                        Alert alert= new Alert("SORRY::","Please Enter a valid Contact",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                    }
            }
        }
    }
}
