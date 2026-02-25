package com.guchasen.talismans.recipe;

import com.guchasen.talismans.Talismans;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Talismans.MOD_ID, TalismanRecipe.Serializer.ID),
                TalismanRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Talismans.MOD_ID, TalismanRecipe.Type.ID),
                TalismanRecipe.Type.INSTANCE);
    }
}
