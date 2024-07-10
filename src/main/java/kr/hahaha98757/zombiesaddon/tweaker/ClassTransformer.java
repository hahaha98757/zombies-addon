//Code by sk1er

package kr.hahaha98757.zombiesaddon.tweaker;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

public class ClassTransformer implements IClassTransformer {
	private final Logger LOGGER = LogManager.getLogger("Command Patcher");

	public ClassTransformer() {
	}

	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (bytes == null) {
			return null;
		} else if (!transformedName.equals("net.minecraftforge.client.ClientCommandHandler")) {
			return bytes;
		} else {
			ClassReader reader = new ClassReader(bytes);
			ClassNode node = new ClassNode();
			reader.accept(node, 8);
			Iterator var6 = node.methods.iterator();

			while (var6.hasNext()) {
				MethodNode methodNode = (MethodNode) var6.next();
				String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(node.name, methodNode.name,
						methodNode.desc);
				if (methodName.equals("executeCommand") || methodName.equals("func_71556_a")) {
					methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), this.checkCommandString());
					break;
				}
			}

			ClassWriter writer = new ClassWriter(2);

			try {
				node.accept(writer);
			} catch (Throwable var9) {
				this.LOGGER.error("Exception when transforming {} : {}",
                        transformedName, var9.getClass().getSimpleName());
				var9.printStackTrace();
			}

			return writer.toByteArray();
		}
	}

	private InsnList checkCommandString() {
		InsnList list = new InsnList();
		list.add(new VarInsnNode(25, 2));
		list.add(new MethodInsnNode(182, "java/lang/String", "trim", "()Ljava/lang/String;", false));
		list.add(new LdcInsnNode("/"));
		list.add(new MethodInsnNode(182, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false));
		LabelNode ifne = new LabelNode();
		list.add(new JumpInsnNode(154, ifne));
		list.add(new InsnNode(3));
		list.add(new InsnNode(172));
		list.add(ifne);
		return list;
	}
}
