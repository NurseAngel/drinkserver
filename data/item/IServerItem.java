package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * ドリンクサーバ素材の マーカーインターフェース<br />
 * 基本的に直接継承先のAbstractItemで実装してて、素手右クリックなどの必要なものだけ個別実装。
 *
 */
public interface IServerItem {

	/**
	 * 素手(空バケツ/ガラス瓶以外)で右クリックした
	 *
	 * @param World
	 * @param EntityPlayer
	 *            叩いたプレイヤー
	 * @param ItemStack
	 *
	 */
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack);

	/**
	 * 空バケツで右クリックした
	 *
	 * @param World
	 * @param EntityPlayer
	 *            叩いたプレイヤー
	 * @param ItemStack
	 *            Item.bucketEmpty
	 */
	public void onBlockRightClickBucket(World world, EntityPlayer entityplayer, ItemStack itemstack);

	/**
	 * ガラス瓶で右クリックした
	 *
	 * @param World
	 * @param EntityPlayer
	 *            叩いたプレイヤー
	 * @param ItemStack
	 *            Item.glassBottle
	 */
	public void onBlockRightClickBottle(World world, EntityPlayer entityplayer, ItemStack itemstack);

	/**
	 * この素材の名前を返す
	 *
	 * @return
	 */
	public String getName();

	/**
	 * この素材アイテム自身を帰す
	 * @return Item
	 */
	public Item getMaterial();

	/**
	 * 手持ちアイテムを一個減らして他のアイテムを一個追加するだけ
	 *
	 * @param entityplayer
	 * @param minusItem
	 * @param plusItem
	 */
	public void changeItem(EntityPlayer entityplayer, ItemStack minusItem, Item plusItem);

}
