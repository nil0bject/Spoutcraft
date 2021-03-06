package org.getspout.spout.entity;

import java.util.List;
import java.util.UUID;

import org.spoutcraft.spoutcraftapi.World;
import org.spoutcraft.spoutcraftapi.entity.Entity;
import org.spoutcraft.spoutcraftapi.property.PropertyObject;
import org.spoutcraft.spoutcraftapi.property.Property;
import org.spoutcraft.spoutcraftapi.util.Location;
import org.spoutcraft.spoutcraftapi.util.Vector;

public class CraftEntity extends PropertyObject implements Entity {
	protected net.minecraft.src.Entity handle = null;
	
	public CraftEntity(net.minecraft.src.Entity handle)
	{
		this.handle = handle;
		addProperty("location", new Property() {
			public void set(Object value) {
				teleport((Location)value);
			}
			public Object get() {
				return getLocation();
			}
		});
		addProperty("velocity", new Property() {
			public void set(Object value) {
				setVelocity((Vector)value);
			}
			public Object get() {
				return getVelocity();
			}
		});
	}
	
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setVelocity(Vector velocity) {
		// TODO Auto-generated method stub

	}

	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean teleport(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean teleport(Entity destination) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Entity> getNearbyEntities(double x, double y, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getEntityId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMaxFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setFireTicks(int ticks) {
		// TODO Auto-generated method stub

	}

	public void remove() {
		// TODO Auto-generated method stub

	}

	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	public Entity getPassenger() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean setPassenger(Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getFallDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setFallDistance(float distance) {
		// TODO Auto-generated method stub

	}

	public UUID getUniqueId() {
		return handle.uniqueId;
	}
}
