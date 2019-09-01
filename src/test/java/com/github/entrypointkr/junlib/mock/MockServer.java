package com.github.entrypointkr.junlib.mock;

import org.bukkit.BanList;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.StructureType;
import org.bukkit.Tag;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class MockServer implements Server {
    private final PluginManager pluginManager;

    public MockServer(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @NotNull
    @Override
    public String getName() {
        return null;
    }

    @NotNull
    @Override
    public String getVersion() {
        return null;
    }

    @NotNull
    @Override
    public String getBukkitVersion() {
        return null;
    }

    @NotNull
    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return null;
    }

    @Override
    public int getMaxPlayers() {
        return 0;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public int getViewDistance() {
        return 0;
    }

    @NotNull
    @Override
    public String getIp() {
        return null;
    }

    @NotNull
    @Override
    public String getWorldType() {
        return null;
    }

    @Override
    public boolean getGenerateStructures() {
        return false;
    }

    @Override
    public boolean getAllowEnd() {
        return false;
    }

    @Override
    public boolean getAllowNether() {
        return false;
    }

    @Override
    public boolean hasWhitelist() {
        return false;
    }

    @Override
    public void setWhitelist(boolean value) {

    }

    @NotNull
    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        return null;
    }

    @Override
    public void reloadWhitelist() {

    }

    @Override
    public int broadcastMessage(@NotNull String message) {
        return 0;
    }

    @NotNull
    @Override
    public String getUpdateFolder() {
        return null;
    }

    @NotNull
    @Override
    public File getUpdateFolderFile() {
        return null;
    }

    @Override
    public long getConnectionThrottle() {
        return 0;
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        return 0;
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        return 0;
    }

    @Nullable
    @Override
    public Player getPlayer(@NotNull String name) {
        return null;
    }

    @Nullable
    @Override
    public Player getPlayerExact(@NotNull String name) {
        return null;
    }

    @NotNull
    @Override
    public List<Player> matchPlayer(@NotNull String name) {
        return null;
    }

    @Nullable
    @Override
    public Player getPlayer(@NotNull UUID id) {
        return null;
    }

    @NotNull
    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @NotNull
    @Override
    public BukkitScheduler getScheduler() {
        return null;
    }

    @NotNull
    @Override
    public ServicesManager getServicesManager() {
        return null;
    }

    @NotNull
    @Override
    public List<World> getWorlds() {
        return null;
    }

    @Nullable
    @Override
    public World createWorld(@NotNull WorldCreator creator) {
        return null;
    }

    @Override
    public boolean unloadWorld(@NotNull String name, boolean save) {
        return false;
    }

    @Override
    public boolean unloadWorld(@NotNull World world, boolean save) {
        return false;
    }

    @Nullable
    @Override
    public World getWorld(@NotNull String name) {
        return null;
    }

    @Nullable
    @Override
    public World getWorld(@NotNull UUID uid) {
        return null;
    }

    @Nullable
    @Override
    public MapView getMap(int id) {
        return null;
    }

    @NotNull
    @Override
    public MapView createMap(@NotNull World world) {
        return null;
    }

    @NotNull
    @Override
    public ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType) {
        return null;
    }

    @NotNull
    @Override
    public ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int radius, boolean findUnexplored) {
        return null;
    }

    @Override
    public void reload() {

    }

    @Override
    public void reloadData() {

    }

    @NotNull
    @Override
    public Logger getLogger() {
        return null;
    }

    @Nullable
    @Override
    public PluginCommand getPluginCommand(@NotNull String name) {
        return null;
    }

    @Override
    public void savePlayers() {

    }

    @Override
    public boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException {
        return false;
    }

    @Override
    public boolean addRecipe(@Nullable Recipe recipe) {
        return false;
    }

    @NotNull
    @Override
    public List<Recipe> getRecipesFor(@NotNull ItemStack result) {
        return null;
    }

    @NotNull
    @Override
    public Iterator<Recipe> recipeIterator() {
        return null;
    }

    @Override
    public void clearRecipes() {

    }

    @Override
    public void resetRecipes() {

    }

    @NotNull
    @Override
    public Map<String, String[]> getCommandAliases() {
        return null;
    }

    @Override
    public int getSpawnRadius() {
        return 0;
    }

    @Override
    public void setSpawnRadius(int value) {

    }

    @Override
    public boolean getOnlineMode() {
        return false;
    }

    @Override
    public boolean getAllowFlight() {
        return false;
    }

    @Override
    public boolean isHardcore() {
        return false;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public int broadcast(@NotNull String message, @NotNull String permission) {
        return 0;
    }

    @NotNull
    @Override
    public OfflinePlayer getOfflinePlayer(@NotNull String name) {
        return null;
    }

    @NotNull
    @Override
    public OfflinePlayer getOfflinePlayer(@NotNull UUID id) {
        return null;
    }

    @NotNull
    @Override
    public Set<String> getIPBans() {
        return null;
    }

    @Override
    public void banIP(@NotNull String address) {

    }

    @Override
    public void unbanIP(@NotNull String address) {

    }

    @NotNull
    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;
    }

    @NotNull
    @Override
    public BanList getBanList(@NotNull BanList.Type type) {
        return null;
    }

    @NotNull
    @Override
    public Set<OfflinePlayer> getOperators() {
        return null;
    }

    @NotNull
    @Override
    public GameMode getDefaultGameMode() {
        return null;
    }

    @Override
    public void setDefaultGameMode(@NotNull GameMode mode) {

    }

    @NotNull
    @Override
    public ConsoleCommandSender getConsoleSender() {
        return null;
    }

    @NotNull
    @Override
    public File getWorldContainer() {
        return null;
    }

    @NotNull
    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return new OfflinePlayer[0];
    }

    @NotNull
    @Override
    public Messenger getMessenger() {
        return null;
    }

    @NotNull
    @Override
    public HelpMap getHelpMap() {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type) {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull String title) {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, int size) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public Merchant createMerchant(@Nullable String title) {
        return null;
    }

    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }

    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }

    @Override
    public boolean isPrimaryThread() {
        return false;
    }

    @NotNull
    @Override
    public String getMotd() {
        return null;
    }

    @Nullable
    @Override
    public String getShutdownMessage() {
        return null;
    }

    @NotNull
    @Override
    public Warning.WarningState getWarningState() {
        return null;
    }

    @NotNull
    @Override
    public ItemFactory getItemFactory() {
        return null;
    }

    @Nullable
    @Override
    public ScoreboardManager getScoreboardManager() {
        return null;
    }

    @Nullable
    @Override
    public CachedServerIcon getServerIcon() {
        return null;
    }

    @NotNull
    @Override
    public CachedServerIcon loadServerIcon(@NotNull File file) throws IllegalArgumentException, Exception {
        return null;
    }

    @NotNull
    @Override
    public CachedServerIcon loadServerIcon(@NotNull BufferedImage image) throws IllegalArgumentException, Exception {
        return null;
    }

    @Override
    public void setIdleTimeout(int threshold) {

    }

    @Override
    public int getIdleTimeout() {
        return 0;
    }

    @NotNull
    @Override
    public ChunkGenerator.ChunkData createChunkData(@NotNull World world) {
        return null;
    }

    @NotNull
    @Override
    public BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
        return null;
    }

    @NotNull
    @Override
    public KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
        return null;
    }

    @NotNull
    @Override
    public Iterator<KeyedBossBar> getBossBars() {
        return null;
    }

    @Nullable
    @Override
    public KeyedBossBar getBossBar(@NotNull NamespacedKey key) {
        return null;
    }

    @Override
    public boolean removeBossBar(@NotNull NamespacedKey key) {
        return false;
    }

    @Nullable
    @Override
    public Entity getEntity(@NotNull UUID uuid) {
        return null;
    }

    @Nullable
    @Override
    public Advancement getAdvancement(@NotNull NamespacedKey key) {
        return null;
    }

    @NotNull
    @Override
    public Iterator<Advancement> advancementIterator() {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@NotNull Material material) {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@NotNull Material material, @Nullable Consumer<BlockData> consumer) {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@NotNull String data) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@Nullable Material material, @Nullable String data) throws IllegalArgumentException {
        return null;
    }

    @Nullable
    @Override
    public <T extends Keyed> Tag<T> getTag(@NotNull String registry, @NotNull NamespacedKey tag, @NotNull Class<T> clazz) {
        return null;
    }

    @NotNull
    @Override
    public <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz) {
        return null;
    }

    @Nullable
    @Override
    public LootTable getLootTable(@NotNull NamespacedKey key) {
        return null;
    }

    @NotNull
    @Override
    public List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public UnsafeValues getUnsafe() {
        return null;
    }

    @NotNull
    @Override
    public Spigot spigot() {
        return null;
    }

    @Override
    public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, @NotNull byte[] message) {

    }

    @NotNull
    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }
}
