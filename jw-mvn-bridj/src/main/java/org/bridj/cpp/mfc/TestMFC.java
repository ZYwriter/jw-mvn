/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bridj.cpp.mfc;

import static org.bridj.cpp.mfc.OnMessage.Type.WM_KEYDOWN;
import static org.bridj.cpp.mfc.StandardAfxCommands.ID_FILE_NEW;
import static org.bridj.cpp.mfc.StandardAfxCommands.ID_FILE_PRINT;
import static org.bridj.cpp.mfc.StandardAfxCommands.ID_FILE_SAVE;

import org.bridj.Pointer;

/**
 * 
 * @author Olivier
 */
public class TestMFC {
    public static void main(String[] args) {

        CWnd wnd = new CWnd() {
            @OnMessage(WM_KEYDOWN)
            //@AfxMsg(WM_KEYDOWN)
            public void OnKeyDown(int a, int b, int c) {

            }
            @OnCommand(ID_FILE_NEW)
            //@AfxCommand
            public void OnSomething() {

            }

            @OnCommandEx({ID_FILE_SAVE, ID_FILE_PRINT})
            public boolean OnSomethingEx(int id) {
                return true;
            }

            @OnUpdateCommand(ID_FILE_NEW)
            //@AfxCommandUpdate
            public void OnUpdateSomething(Pointer<CCmdUI> pCmdUI) {
                if (pCmdUI == null)
                    return;

                pCmdUI.get().Enable(true);
            }

            @OnRegisteredMessage("MYAPP_MYMESSAGE")
            //@OnRegisteredMessage("MYAPP_MYMESSAGE") // RegisterWindowMessage
            public void OnMyMessage(int a, int b, int c) {

            }
        };
    }
}
