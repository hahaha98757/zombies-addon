//Code by sk1er

package kr.hahaha98757.zombiesaddon.tweaker;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class ClassTweaker implements IFMLLoadingPlugin {
	public ClassTweaker() {
	}

	public String[] getASMTransformerClass() {
		return new String[] { ClassTransformer.class.getName(), SpawnTimeRendererTransformer.class.getName(),
				ZombiesUtilsTransformer.class.getName() };
	}

	public String getModContainerClass() {
		return null;
	}

	public String getSetupClass() {
		return null;
	}

	public void injectData(Map<String, Object> data) {
	}

	public String getAccessTransformerClass() {
		return null;
	}
}
