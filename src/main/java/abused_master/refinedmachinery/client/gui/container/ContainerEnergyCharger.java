package abused_master.refinedmachinery.client.gui.container;

import abused_master.abusedlib.client.gui.OutputSlot;
import abused_master.abusedlib.utils.InventoryHelper;
import abused_master.refinedmachinery.tiles.machine.BlockEntityEnergyCharger;
import net.minecraft.container.Container;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerEnergyCharger extends Container {

    public final Inventory inventory;
    public final PlayerInventory playerInventory;
    public final World world;
    public final PropertyDelegate propertyDelegate;

    public ContainerEnergyCharger(int syncId, PlayerInventory playerInventory, BlockEntityEnergyCharger charger) {
        super(null, syncId);
        this.inventory = charger;
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.world;
        this.propertyDelegate = charger.property;

        this.addSlot(new Slot(inventory, 0, 56, 26) {
            @Override
            public int getMaxStackAmount() {
                return 1;
            }
        });
        this.addSlot(new OutputSlot(inventory, 1, 112, 26));
        this.addProperties(charger.property);

        int i;
        for(i = 0; i < 3; ++i) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + i * 9 + 9, 8 + var4 * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return this.inventory.canPlayerUseInv(playerEntity);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int int_1) {
        return InventoryHelper.handleShiftClick(this, player, int_1);
    }
}
