package gameplay;

import java.awt.Color;

public class BulletSpecs {

	private int width = 0;
	private int height = 0;
	private Color color = null;
	private float velocity = 0;
	private int cooldown = 0;
	private float damage = 0;

	private boolean piercing = false;
	private boolean bouncing = false;

	public BulletSpecs() {}

	public int getWidth() {
		return width;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(final float velocity) {
		this.velocity = velocity;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(final int cooldown) {
		this.cooldown = cooldown;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(final float damage) {
		this.damage = damage;
	}

	public boolean isPiercing() {
		return piercing;
	}

	public void setPiercing(final boolean piercing) {
		this.piercing = piercing;
	}

	public boolean isBouncing() {
		return bouncing;
	}

	public void setBouncing(final boolean bouncing) {
		this.bouncing = bouncing;
	}
}
