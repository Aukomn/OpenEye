package com.aukomn.myopeneye.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class TabInfo implements Serializable {

    private TabInfoBean tabInfo;

    public TabInfoBean getTabInfoBean() {
        return tabInfo;
    }

    public void setTabInfoBean(TabInfoBean tabInfoBean) {
        this.tabInfo = tabInfoBean;
    }

    public static class TabInfoBean{
        private int defaultIdx;

        public int getDefaultIdx() {
            return defaultIdx;
        }

        public void setDefaultIdx(int defaultIdx) {
            this.defaultIdx = defaultIdx;
        }

        public ArrayList<Tabs> getTabList() {
            return tabList;
        }

        public void setTabList(ArrayList<Tabs> tabList) {
            this.tabList = tabList;
        }

        private ArrayList<Tabs> tabList;
    }
}
