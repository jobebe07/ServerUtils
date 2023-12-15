# Server Utils

Library for PaperMC with several utilities making it
easier to create and use GUI elements.

# Features
### ItemStackBuilder
```java
ItemStack stack = ItemStackBuilder.of(Material.TRIDENT)
        .count(50)
            .meta()     // Starts meta section
        .displayName(Component.text("some name"))
        .lore(Component.text("some desc"))
        .enchant(Enchantment.DURABILITY)
        .flags(ItemFlag.HIDE_ATTRIBUTES)
            .finish()   // Ends meta section
        .build();
```
_The Constructor_
```java
new ItemStackBuilder(Material.TRIDENT);
new ItemStackBuilder(new ItemStack(Material.STONE, 64));
// or
        ItemStackBuilder.of(Material.TRIDENT);
ItemStackBuilder.of(new ItemStack(Material.STONE, 64));
```
_Modify amount_
```java
new ItemStackBuilder(Material.TRIDENT)
        .count(3)
        .build();
```
_Modify ItemMeta_
```java
new ItemStackBuilder(Material.TRIDENT)
        .meta()     // you are now using the ItemMetaBuilder
        .displayName("Some name")
        
        .clearEnchant()
        .enchant(Enchantment.RIPTIDE) // (..., int level, boolean ignoreRestriction) optional

        .clearFlags()
        .flags(ItemFlag.HIDE_ATTRIBUTES)

        .clearLore()
        .lore(Component.text("Line 1"), Component.text("Line 2"))
        .lore(Component.text("Line 3")),
    
        .finish()   // get back the ItemStackBuilder
        .build();   // returns the final ItemStack
```

### Custom Menus
```java
Menu menu = new Menu(Component.text("Inv"), 3, true);
MenuButton btn = new MenuButton(Material.ALLIUM,
        Component.text("Hi"),
        List.of(Component.text("some lore")),
        false);
btn.onClick(menuButton -> {
    menuButton.addGlint();
});

//btn.onClick(MenuButton::addGlint);
menu.setButton(0, 5, btn);
```
### ItemCache
```java
// register your plugin in onEnable()
ServerUtils.initialize(this);
...

ItemStack stack = ItemCache.placeholder();
ItemCache.setDefaultPlaceholder(/* your custom item */);

ItemCache.clear();

if(!ItemCache.hasKey("placeholder")) {
    ItemCache.save("placeholder", stack);
}

ItemCache.get("placeholder");
ItemCache.delete("placeholder");
```