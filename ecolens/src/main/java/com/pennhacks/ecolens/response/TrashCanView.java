package com.pennhacks.ecolens.response;

import com.pennhacks.ecolens.model.CurrentTrashCanItem;
import com.pennhacks.ecolens.model.LifetimeTrashCanItem;

import java.util.List;

public class TrashCanView {
        private List<CurrentTrashCanItem> currentTrashCanItems;
        private List<LifetimeTrashCanItem> lifetimeTrashCanItems;

        public TrashCanView(List<CurrentTrashCanItem> currentTrashCanItems, List<LifetimeTrashCanItem> lifetimeTrashCanItems) {
                this.currentTrashCanItems = currentTrashCanItems;
                this.lifetimeTrashCanItems = lifetimeTrashCanItems;
        }

        public TrashCanView() {
        }

        public List<CurrentTrashCanItem> getCurrentTrashCanItems() {
                return currentTrashCanItems;
        }

        public void setCurrentTrashCanItems(List<CurrentTrashCanItem> currentTrashCanItems) {
                this.currentTrashCanItems = currentTrashCanItems;
        }

        public List<LifetimeTrashCanItem> getLifetimeTrashCanItems() {
                return lifetimeTrashCanItems;
        }

        public void setLifetimeTrashCanItems(List<LifetimeTrashCanItem> lifetimeTrashCanItems) {
                this.lifetimeTrashCanItems = lifetimeTrashCanItems;
        }

        
        public String toString() {
                return "TrashCanView{" +
                        "currentTrashCanItems=" + currentTrashCanItems +
                        ", lifetimeTrashCanItems=" + lifetimeTrashCanItems +
                        '}';
        }
}
