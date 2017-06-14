package com.xiyuan.copypath.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ui.TextTransferable;

/**
 * Created by xiyuan_fengyu on 2017/6/14.
 */
public class CopySysIndependentPathAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFile file = CommonDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if (file != null) {
            String sysIndependentPath = toSystemIndependentPaths(file.getPath());
            CopyPasteManager.getInstance().setContents(new TextTransferable(sysIndependentPath));
        }
    }

    public static String toSystemIndependentPaths(String paths) {
        String[] splitPaths = paths.trim().split(";");
        for (int i = 0; i < splitPaths.length; i++) {
            splitPaths[i] = com.intellij.openapi.util.io.FileUtil.toSystemIndependentName(splitPaths[i]);
        }
        return StringUtil.join(splitPaths, ";");
    }

}
