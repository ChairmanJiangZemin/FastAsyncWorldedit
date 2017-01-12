package com.boydti.fawe.object.clipboard;

import com.boydti.fawe.object.RunnableVal2;
import com.sk89q.jnbt.CompoundTag;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;

public class OffsetFaweClipboard extends AbstractDelegateFaweClipboard {
    private final int ox, oy, oz;

    public OffsetFaweClipboard(FaweClipboard parent, int ox, int oy, int oz) {
        super(parent);
        this.ox = ox;
        this.oy = oy;
        this.oz = oz;
    }

    public OffsetFaweClipboard(FaweClipboard parent, int offset) {
        this(parent, offset, offset, offset);
    }

    @Override
    public BaseBlock getBlock(int x, int y, int z) {
        return super.getBlock(x + ox, y + oy, z + oz);
    }

    @Override
    public boolean setBlock(int x, int y, int z, BaseBlock block) {
        return super.setBlock(ox + x, oy + y, oz + z, block);
    }

    @Override
    public boolean setTile(int x, int y, int z, CompoundTag tag) {
        return super.setTile(ox + x, oy + y, oz + z, tag);
    }

    @Override
    public void forEach(final RunnableVal2<Vector, BaseBlock> task, boolean air) {
        super.forEach(new RunnableVal2<Vector, BaseBlock>() {
            @Override
            public void run(Vector value, BaseBlock block) {
                value.mutX(value.getX() - ox);
                value.mutY(value.getY() - oy);
                value.mutZ(value.getZ() - oz);
                task.run(value, block);
            }
        }, air);
    }
}
