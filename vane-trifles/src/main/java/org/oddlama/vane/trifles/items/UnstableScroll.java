package org.oddlama.vane.trifles.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.oddlama.vane.core.config.recipes.RecipeList;
import org.oddlama.vane.core.config.recipes.ShapedRecipeDefinition;
import org.oddlama.vane.core.module.Context;
import org.oddlama.vane.trifles.Trifles;
import org.oddlama.vane.trifles.event.PlayerTeleportScrollEvent;
import org.oddlama.vane.util.Util;

public class UnstableScroll extends Scroll {
	public static final NamespacedKey LAST_SCROLL_TELEPORT_LOCATION = Util.namespaced_key("vane", "last_scroll_teleport_location");

	public UnstableScroll(Context<Trifles> context) {
		super(context, "unstable_scroll", 6000);
	}

	@Override
	public RecipeList default_recipes() {
		return RecipeList.of(new ShapedRecipeDefinition("generic")
			.shape("pip", "cbe", "plp")
			// TODO BADDDDDDDDDDDDDDDDDDDDDDDDDDDD TEST REMOVEEEEEEEEEEEEEEEEE
			.set_ingredient('b', "minecraft:stick{Enchantments:[{id:knockback,lvl:1000}]}")
			.set_ingredient('p', Material.MAP)
			.set_ingredient('i', Material.CHORUS_FRUIT)
			.set_ingredient('c', Material.COMPASS)
			.set_ingredient('e', Material.ENDER_PEARL)
			.set_ingredient('l', Material.CLOCK)
			.result(key().toString()));
	}

	@Override
	public Location teleport_location(Player player, boolean imminent_teleport) {
		return Util.storage_get_location(player.getPersistentDataContainer(), LAST_SCROLL_TELEPORT_LOCATION, null);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void on_player_teleport_scroll(final PlayerTeleportScrollEvent event) {
		Util.storage_set_location(event.getPlayer().getPersistentDataContainer(), LAST_SCROLL_TELEPORT_LOCATION, event.getFrom());
	}
}
