//Code by sk1er

package kr.hahaha98757.zombiesaddon.tweaker;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class ClassTweaker implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { SpawnTimeRendererTransformer.class.getName(), ZombiesUtilsTransformer.class.getName() };
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}