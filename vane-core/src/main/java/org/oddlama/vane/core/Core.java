package org.oddlama.vane.core;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.oddlama.vane.annotation.VaneModule;
import org.oddlama.vane.annotation.config.ConfigVersion;
import org.oddlama.vane.annotation.lang.LangString;
import org.oddlama.vane.annotation.lang.LangMessage;
import org.oddlama.vane.annotation.lang.LangVersion;
import org.oddlama.vane.core.module.Module;
import org.oddlama.vane.util.Message;

@VaneModule(name = "core", bstats = 8637, config_version = 1, lang_version = 1, storage_version = 2)
public class Core extends Module<Core> {
	@LangString
	public String lang_command_not_a_player;
	@LangString
	public String lang_command_permission_denied;

	@LangMessage
	public Message lang_invalid_time_format;

	// Module registry
	private SortedSet<Module<?>> vane_modules = new TreeSet<>((a, b) -> a.get_name().compareTo(b.get_name()));
	public void register_module(Module<?> module) { vane_modules.add(module); }
	public void unregister_module(Module<?> module) { vane_modules.remove(module); }
	public SortedSet<Module<?>> get_modules() { return Collections.unmodifiableSortedSet(vane_modules); }

	public Core() {
		// Components
		new org.oddlama.vane.core.commands.Vane(this);
		//new TabCompletionRestricter(this);

		add_storage_migration_to(1, "initializer", map -> {});
		add_storage_migration_to(2, "test", map -> {
			System.out.println(map.get(storage_path_of("storage_version")));
		});
	}
}
