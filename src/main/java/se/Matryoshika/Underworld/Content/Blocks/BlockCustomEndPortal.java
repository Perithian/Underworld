package se.Matryoshika.Underworld.Content.Blocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.TileEntity.TileUnderworldEnderPortal;

public class BlockCustomEndPortal extends BlockEndPortal{

	public BlockCustomEndPortal() {
		super(Material.PORTAL);
		this.setHardness(5);
		this.setResistance(2000);
		this.setLightLevel(1.0F);
		this.setRegistryName("BlockEndPortal");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
		this.setTickRandomly(false);
	}
	
	@Override
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 1.1D, 1D);
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state){
        return true;
    }
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state){
        return new TileUnderworldEnderPortal();
    }
	
	@Override
	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side){
        return true;
    }
	
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side){
        return true;
    }
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.INVISIBLE;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
        return;
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if (!player.isRiding() && !world.isRemote && heldItem != null && heldItem.getItem() == Items.ENDER_EYE){
			heldItem.stackSize--;
			player.changeDimension(1);
		}
		return true;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
        return;
    }
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn){
        addCollisionBoxToList(pos, entityBox, collidingBoxes, getBoundingBox(state, worldIn, pos));
    }
	
	@Override
	@Nullable
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state){
        return new ItemStack(this);
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
    }
	
	@Override
	public int quantityDropped(Random random){
        return 1;
    }

}