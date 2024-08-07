// Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.data;

public class Window {
	private final short[] xyz = new short[3];
	private final byte id;
	private boolean isActive;

	public Window(int id, int x, int y, int z) {
		this.id = (byte) id;
		xyz[0] = (short) x;
		xyz[1] = (short) y;
		xyz[2] = (short) z;
	}

	public int getID() {
		return id;
	}

	public short[] getXYZ() {
		return xyz;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isActive() {
		return isActive;
	}
}