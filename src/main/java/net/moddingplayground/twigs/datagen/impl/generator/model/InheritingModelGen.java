package net.moddingplayground.twigs.datagen.impl.generator.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.Twigs;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class InheritingModelGen implements ModelGen {
    private final Identifier parent;
    private final Map<String, String> textureRef = new LinkedHashMap<>();

    public InheritingModelGen(Identifier parent) {
        this.parent = parent;
    }
    public InheritingModelGen(String parent) {
        this(Identifier.tryParse(parent));
    }

    public InheritingModelGen texture(String reference, String newReference) {
        textureRef.put(reference, newReference);
        return this;
    }

    public InheritingModelGen texture(String reference, Identifier id) {
        textureRef.put(reference, id.toString());
        return this;
    }

    @Override
    public JsonElement makeJson(Identifier name) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", parent.toString());
        if (!textureRef.isEmpty()) {
            JsonObject textures = new JsonObject();
            for (Map.Entry<String, String> ref : textureRef.entrySet()) {
                textures.addProperty(ref.getKey(), ref.getValue());
            }
            root.add("textures", textures);
        }
        return root;
    }

    public static InheritingModelGen inherit(String parent) {
        return new InheritingModelGen(parent);
    }
    public static InheritingModelGen inherit(Identifier parent) {
        return inherit(parent.toString());
    }

    public static InheritingModelGen cubeAll(Identifier texture) {
        return new InheritingModelGen("block/cube_all")
                   .texture("all", texture);
    }

    public static InheritingModelGen leaves(Identifier texture) {
        return new InheritingModelGen("block/leaves")
                   .texture("all", texture);
    }

    public static InheritingModelGen slabAll(Identifier texture) {
        return new InheritingModelGen("block/slab")
                   .texture("bottom", texture)
                   .texture("top", texture)
                   .texture("side", texture);
    }

    public static InheritingModelGen slabAllTop(Identifier texture) {
        return new InheritingModelGen("block/slab_top")
                   .texture("bottom", texture)
                   .texture("top", texture)
                   .texture("side", texture);
    }

    public static InheritingModelGen slab(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen("block/slab")
                   .texture("bottom", bottom)
                   .texture("top", top)
                   .texture("side", side);
    }

    public static InheritingModelGen slabTop(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen("block/slab_top")
                   .texture("bottom", bottom)
                   .texture("top", top)
                   .texture("side", side);
    }

    public static InheritingModelGen stairsAll(Identifier texture) {
        return new InheritingModelGen("block/stairs")
                   .texture("bottom", texture)
                   .texture("top", texture)
                   .texture("side", texture);
    }

    public static InheritingModelGen stairsAllInner(Identifier texture) {
        return new InheritingModelGen("block/inner_stairs")
                   .texture("bottom", texture)
                   .texture("top", texture)
                   .texture("side", texture);
    }

    public static InheritingModelGen stairsAllOuter(Identifier texture) {
        return new InheritingModelGen("block/outer_stairs")
                   .texture("bottom", texture)
                   .texture("top", texture)
                   .texture("side", texture);
    }

    public static InheritingModelGen stairs(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen("block/stairs")
                   .texture("bottom", bottom)
                   .texture("top", top)
                   .texture("side", side);
    }

    public static InheritingModelGen stairsInner(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen("block/inner_stairs")
                   .texture("bottom", bottom)
                   .texture("top", top)
                   .texture("side", side);
    }

    public static InheritingModelGen stairsOuter(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen("block/outer_stairs")
                   .texture("bottom", bottom)
                   .texture("top", top)
                   .texture("side", side);
    }

    public static InheritingModelGen cubeMirroredAll(Identifier texture) {
        return new InheritingModelGen("block/cube_mirrored_all")
                   .texture("all", texture);
    }

    public static InheritingModelGen cubeColumn(Identifier end, Identifier side) {
        return new InheritingModelGen("block/cube_column")
                   .texture("end", end)
                   .texture("side", side);
    }

    public static InheritingModelGen cubeColumnHoriz(Identifier end, Identifier side) {
        return new InheritingModelGen("block/cube_column_horizontal")
                   .texture("end", end)
                   .texture("side", side);
    }

    public static InheritingModelGen horizontalOrientable(Identifier face, Identifier others) {
        return new InheritingModelGen("block/orientable")
                   .texture("top", others)
                   .texture("front", face)
                   .texture("side", others);
    }

    public static InheritingModelGen cubeBottomTop(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen("block/cube_bottom_top")
                   .texture("bottom", bottom)
                   .texture("top", top)
                   .texture("side", side);
    }

    public static InheritingModelGen cube(Identifier north, Identifier east, Identifier south, Identifier west, Identifier up, Identifier down) {
        return new InheritingModelGen("block/cube")
                   .texture("north", north)
                   .texture("east", east)
                   .texture("south", south)
                   .texture("west", west)
                   .texture("up", up)
                   .texture("down", down)
                   .texture("particle", down);
    }

    public static InheritingModelGen cubeMirrored(Identifier north, Identifier east, Identifier south, Identifier west, Identifier up, Identifier down) {
        return new InheritingModelGen("block/cube_mirrored")
                   .texture("north", north)
                   .texture("east", east)
                   .texture("south", south)
                   .texture("west", west)
                   .texture("up", up)
                   .texture("down", down)
                   .texture("particle", down);
    }

    public static InheritingModelGen cubeFrontSided(Identifier front, Identifier side, Identifier top, Identifier bottom) {
        return new InheritingModelGen("block/cube")
                   .texture("north", front)
                   .texture("east", side)
                   .texture("south", side)
                   .texture("west", side)
                   .texture("up", top)
                   .texture("down", bottom)
                   .texture("particle", bottom);
    }

    public static InheritingModelGen cubeSeparateSided(Identifier frontBack, Identifier side, Identifier top, Identifier bottom) {
        return new InheritingModelGen("block/cube")
                   .texture("north", frontBack)
                   .texture("east", side)
                   .texture("south", frontBack)
                   .texture("west", side)
                   .texture("up", top)
                   .texture("down", bottom)
                   .texture("particle", bottom);
    }

    public static InheritingModelGen cubeFrontBackSided(Identifier front, Identifier back, Identifier side, Identifier top, Identifier bottom) {
        return new InheritingModelGen("block/cube")
                   .texture("north", front)
                   .texture("east", side)
                   .texture("south", back)
                   .texture("west", side)
                   .texture("up", top)
                   .texture("down", bottom)
                   .texture("particle", bottom);
    }

    public static InheritingModelGen generated(Identifier... layers) {
        InheritingModelGen gen = new InheritingModelGen("item/generated");
        for (int i = 0, l = layers.length; i < l; i++) {
            gen.texture("layer" + i, layers[i]);
        }
        return gen;
    }

    public static InheritingModelGen cross(Identifier texture) {
        return new InheritingModelGen("block/cross")
                   .texture("cross", texture);
    }

    public static InheritingModelGen tintedCross(Identifier texture) {
        return new InheritingModelGen("block/tinted_cross")
                   .texture("cross", texture);
    }

    public static InheritingModelGen flowerPotCross(Identifier texture) {
        return new InheritingModelGen("block/flower_pot_cross")
            .texture("plant", texture);
    }

    public static InheritingModelGen carpet(Identifier texture) {
        return new InheritingModelGen("block/carpet")
            .texture("wool", texture);
    }

    public static InheritingModelGen fenceSide(Identifier texture) {
        return new InheritingModelGen("block/fence_side")
                   .texture("texture", texture);
    }

    public static InheritingModelGen fencePost(Identifier texture) {
        return new InheritingModelGen("block/fence_post")
                   .texture("texture", texture);
    }

    public static InheritingModelGen fenceInventory(Identifier texture) {
        return new InheritingModelGen("block/fence_inventory")
                   .texture("texture", texture);
    }

    public static InheritingModelGen wallSide(Identifier texture) {
        return new InheritingModelGen("block/template_wall_side")
                   .texture("wall", texture);
    }

    public static InheritingModelGen wallSideTall(Identifier texture) {
        return new InheritingModelGen("block/template_wall_side_tall")
                   .texture("wall", texture);
    }

    public static InheritingModelGen wallPost(Identifier texture) {
        return new InheritingModelGen("block/template_wall_post")
                   .texture("wall", texture);
    }

    public static InheritingModelGen wallInventory(Identifier texture) {
        return new InheritingModelGen("block/wall_inventory")
                   .texture("wall", texture);
    }

    public static InheritingModelGen wallSidedSide(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_wall_sided_side"))
            .texture("bottom", bottom)
            .texture("top", top)
            .texture("side", side);
    }

    public static InheritingModelGen wallSidedSideTall(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_wall_sided_side_tall"))
            .texture("bottom", bottom)
            .texture("top", top)
            .texture("side", side);
    }

    public static InheritingModelGen wallSidedPost(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_wall_sided_post"))
            .texture("bottom", bottom)
            .texture("top", top)
            .texture("side", side);
    }

    public static InheritingModelGen wallSidedInventory(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/wall_sided_inventory"))
            .texture("bottom", bottom)
            .texture("top", top)
            .texture("side", side);
    }

    public static InheritingModelGen doorTop(Identifier top, Identifier bottom, boolean rightHinge) {
        return new InheritingModelGen("block/door_top" + (rightHinge ? "_rh" : ""))
                   .texture("top", top)
                   .texture("bottom", bottom);
    }

    public static InheritingModelGen doorBottom(Identifier bottom, boolean rightHinge) {
        return new InheritingModelGen("block/door_bottom" + (rightHinge ? "_rh" : ""))
                   .texture("bottom", bottom);
    }

    public static InheritingModelGen trapdoorTop(Identifier texture) {
        return new InheritingModelGen("block/template_orientable_trapdoor_top")
                   .texture("texture", texture);
    }

    public static InheritingModelGen trapdoorBottom(Identifier texture) {
        return new InheritingModelGen("block/template_orientable_trapdoor_bottom")
                   .texture("texture", texture);
    }

    public static InheritingModelGen trapdoorOpen(Identifier texture) {
        return new InheritingModelGen("block/template_orientable_trapdoor_open")
                   .texture("texture", texture);
    }

    public static InheritingModelGen pressurePlate(Identifier texture, boolean down) {
        return new InheritingModelGen("block/pressure_plate_" + (down ? "down" : "up"))
                   .texture("texture", texture);
    }

    public static InheritingModelGen button(Identifier texture, boolean pressed) {
        return new InheritingModelGen("block/button" + (pressed ? "_pressed" : ""))
                   .texture("texture", texture);
    }

    public static InheritingModelGen buttonInventory(Identifier texture) {
        return new InheritingModelGen("block/button_inventory")
                   .texture("texture", texture);
    }

    public static InheritingModelGen fenceGate(Identifier texture, boolean open) {
        return new InheritingModelGen("block/template_fence_gate" + (open ? "_open" : ""))
                   .texture("texture", texture);
    }

    public static InheritingModelGen fenceGateWall(Identifier texture, boolean open) {
        return new InheritingModelGen("block/template_fence_gate_wall" + (open ? "_open" : ""))
                   .texture("texture", texture);
    }

    public static InheritingModelGen torch(Identifier texture) {
        return new InheritingModelGen(new Identifier("block/template_torch"))
            .texture("torch", texture);
    }

    public static ModelGen wallPlantThin(Identifier texture) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_wall_plant_thin"))
            .texture("texture", texture);
    }
}
