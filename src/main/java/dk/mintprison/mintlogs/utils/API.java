package dk.mintprison.mintlogs.utils;

import dk.mintprison.mintlogs.MintLogs;

public class API {
    public enum ItemType {
        HEAD,
        ITEM
    }
    /**
    @Param type: ItemType.HEAD eller ItemType.ITEM
    @Param id: ID på itemet. (Head: 42167, Item: DIAMOND_BLOCK)
    @Param antal: Antal der skal tilføjes.
     */
    public static void addLog(API.ItemType type, String id, Integer antal){
        if (type.equals(ItemType.HEAD)){
            MintLogs.logsYML.set("heads."+id, (antal+MintLogs.logsYML.getInt("heads."+id)));
        } else if (type.equals(ItemType.ITEM)) {
            MintLogs.logsYML.set("items."+id, (antal+MintLogs.logsYML.getInt("items."+id)));
        }
    }

    /**
     @Param type: ItemType.HEAD eller ItemType.ITEM
     @Param id: ID på itemet. (Head: 42167, Item: DIAMOND_BLOCK)
     @Param antal: Antal der skal fjernes.
     */
    public static void removeLog(ItemType type, String id, Integer antal){
        if (type.equals(ItemType.HEAD)){
            int currentAmount = MintLogs.logsYML.getInt("heads."+id);
            int newAmount = currentAmount - antal;
            MintLogs.logsYML.set("heads."+id, newAmount < 0 ? 0 : newAmount);
        } else if (type.equals(ItemType.ITEM)) {
            int currentAmount = MintLogs.logsYML.getInt("items."+id);
            int newAmount = currentAmount - antal;
            MintLogs.logsYML.set("items."+id, newAmount < 0 ? 0 : newAmount);
        }
    }

    /**
     @Param type: ItemType.HEAD eller ItemType.ITEM
     @Param id: ID på itemet. (Head: 42167, Item: DIAMOND_BLOCK)
     @Param antal: Antal der skal være i alt.
     */

    public static void setLog(ItemType type, String id, Integer antal){
        if (antal < 0) {
            antal = 0;
        }
        if (type.equals(ItemType.HEAD)){
            MintLogs.logsYML.set("heads."+id, antal);
        } else if (type.equals(ItemType.ITEM)) {
            MintLogs.logsYML.set("items."+id, antal);
        }
    }


}
