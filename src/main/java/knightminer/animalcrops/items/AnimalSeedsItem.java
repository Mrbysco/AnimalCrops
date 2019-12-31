package knightminer.animalcrops.items;

import knightminer.animalcrops.core.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class AnimalSeedsItem extends BlockItem {

	public AnimalSeedsItem(Block crops, Properties props) {
		super(crops, props);
	}

	// restore default, we call seeds seeds and crops crops
  @Override
  public String getTranslationKey() {
    return this.getDefaultTranslationKey();
  }

	@Override
  public ITextComponent getDisplayName(ItemStack stack) {
    return Utils.getEntityID(stack.getTag())
                .flatMap(EntityType::byKey)
                .map(EntityType::getTranslationKey)
                .map((key) -> new TranslationTextComponent(this.getTranslationKey(), new TranslationTextComponent(key)))
                .orElseGet(() -> new TranslationTextComponent(this.getTranslationKey() + ".default"));
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);
    tooltip.add(new TranslationTextComponent(this.getTranslationKey() + ".tooltip"));
  }

  @Deprecated
  public ItemStack makeSeed(ResourceLocation entity) {
    if(entity == null) {
        return new ItemStack(this);
    }
		return makeSeed(entity.toString());
  }

  /**
   * Makes a seed stack from the given entity ID
   * @param entity  Entity ID
   * @return  Seed containing that entity
   * @deprecated  Use {@link Utils::setEntityId(ItemStack, String)}
   */
  @Deprecated
  public ItemStack makeSeed(String entity) {
    return Utils.setEntityId(new ItemStack(this), entity);
  }
}
