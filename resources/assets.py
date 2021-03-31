from mcresources import ResourceManager


def generate(rm: ResourceManager):

    rm.blockstate('rainbow_log', variants={
        'axis=x': {'model': 'rainbowoaks:block/rainbow_log_horizontal', 'x': 90, 'y': 90},
        'axis=y': {'model': 'rainbowoaks:block/rainbow_log'},
        'axis=z': {'model': 'rainbowoaks:block/rainbow_log_horizontal', 'x': 90}
    }).with_lang(lang('Rainbow Log')).with_block_loot('rainbowoaks:rainbow_log')
    rm.blockstate('rainbow_leaves', model='rainbowoaks:block/rainbow_leaves').with_lang(lang('Rainbow Leaves'))
    rm.blockstate('rainbow_sapling', model='rainbowoaks:block/rainbow_sapling').with_lang(lang('Rainbow Sapling')).with_block_loot('rainbowoaks:rainbow_sapling')

    rm.block_model('rainbow_log', parent='minecraft:block/cube_column', textures={
        'end': 'rainbowoaks:block/top',
        'side': 'rainbowoaks:block/side'
    }).with_item_model().with_tag('minecraft:logs').with_tag('minecraft:logs_that_burn')

    rm.block_model('rainbow_log_horizontal', parent='minecraft:block/cube_column_horizontal', textures={
        'end': 'rainbowoaks:block/top',
        'side': 'rainbowoaks:block/side'
    })

    rm.block_model('rainbow_sapling', parent='minecraft:block/cross', textures={'cross': 'rainbowoaks:block/sapling'})
    rm.item_model('rainbow_sapling', 'rainbowoaks:block/sapling')

    rm.block_model('rainbow_leaves', parent='minecraft:block/oak_leaves', no_textures=True).with_tag('minecraft:leaves')
    rm.item_model('rainbow_leaves', parent='minecraft:item/oak_leaves', no_textures=True)

    rm.crafting_shapeless('rainbow_log_to_planks', 'rainbowoaks:rainbow_log', (4, 'minecraft:oak_planks')).with_advancement('rainbowoaks:rainbow_log')
    rm.lang('rainbowoaks.config.server.rarity', 'Rarity of a rainbow tree patch in 1/N chunks')
    rm.lang('rainbowoaks.config.server.extraAttempts', 'Extra trees that spawn in a patch')
    rm.lang('rainbowoaks.config.server.rarity', 'Resource Locations of biomes they spawn in. Separate with commas.')


def lang(key: str, *args) -> str:
    return ((key % args) if len(args) > 0 else key).replace('_', ' ').replace('/', ' ').title()

