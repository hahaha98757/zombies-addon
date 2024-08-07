package kr.hahaha98757.zombiesaddon.tweaker;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ZombiesUtilsTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (bytes == null) return null;
        else if (!transformedName.equals("com.github.stachelbeere1248.zombiesutils.handlers.RenderGameOverlayHandler"))
            return bytes;

		ClassReader reader = new ClassReader(bytes);
		ClassNode node = new ClassNode();
		reader.accept(node, 8);

		for (MethodNode methodNode : node.methods) {
			String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(node.name, methodNode.name, methodNode.desc);

			if (!methodName.equals("renderTime")) continue;
			methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), this.addCode());
			break;
		}

		ClassWriter writer = new ClassWriter(2);

		try {
			node.accept(writer);
		} catch (Throwable var9) {
			var9.printStackTrace();
		}

		return writer.toByteArray();
	}

	private InsnList addCode() {
		InsnList list = new InsnList();

		if (isTransformer()) {
			list.add(new InsnNode(Opcodes.RETURN));
		}

		return list;
	}

	public static boolean isTransformer() {
		try {
			return readFile().equals("true");
		} catch (Exception e) {
			return true;
		}
	}

	private static String readFile() {
		try {
			Path filePath = Paths.get("config/zombiesaddonZombiesUtilsSetting.txt");

			List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

            return lines.get(1);
		} catch (IOException e) {
			writeFile();
			return null;
		}
	}

	private static void writeFile() {
		try {
			Path filePath = Paths.get("config/zombiesaddonZombiesUtilsSetting.txt");

			List<String> content = Arrays.asList("#Turn off the timer of Zombies Utils.", "true");

			Files.write(filePath, content, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}