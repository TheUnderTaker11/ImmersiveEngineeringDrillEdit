/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.common.util.compat.jei.blastfurnace;

import blusunrize.immersiveengineering.api.Lib;
import blusunrize.immersiveengineering.api.crafting.BlastFurnaceFuel;
import blusunrize.immersiveengineering.client.ClientUtils;
import blusunrize.immersiveengineering.common.util.Utils;
import blusunrize.immersiveengineering.common.util.compat.jei.IERecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class BlastFurnaceFuelCategory extends IERecipeCategory<BlastFurnaceFuel>
{
	public static final ResourceLocation UID = new ResourceLocation(Lib.MODID, "blastfurnace_fuel");
	private final IDrawableAnimated flame;

	public BlastFurnaceFuelCategory(IGuiHelper helper)
	{
		super(BlastFurnaceFuel.class, helper, UID, "gui.immersiveengineering.blastFurnace.fuel");
		ResourceLocation furnaceBackgroundLocation = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
		setBackground(helper.drawableBuilder(furnaceBackgroundLocation, 55, 36, 18, 36).addPadding(0, 0, 0, 68).build());
		setIcon(helper.createDrawable(new ResourceLocation(Lib.MODID, "textures/gui/blast_furnace.png"), 176, 0, 14, 14));

		IDrawableStatic flameStatic = helper.createDrawable(furnaceBackgroundLocation, 176, 0, 14, 14);
		this.flame = helper.createAnimatedDrawable(flameStatic, 20*4, IDrawableAnimated.StartDirection.TOP, true);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BlastFurnaceFuel recipe, List<? extends IFocus<?>> focuses)
	{
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 17)
				.addItemStacks(Arrays.asList(recipe.input.getItems()));
	}

	@Override
	public void draw(BlastFurnaceFuel recipe, IRecipeSlotsView recipeSlotsView, PoseStack transform, double mouseX, double mouseY)
	{
		this.flame.draw(transform, 1, 0);
		String burnTime = I18n.get("desc.immersiveengineering.info.seconds", Utils.formatDouble(recipe.burnTime/20, "#.##"));
		ClientUtils.font().draw(transform, burnTime, 24, 12, 0x777777);
	}
}