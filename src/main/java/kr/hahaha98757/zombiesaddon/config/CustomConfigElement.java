//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.config;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiEditArrayEntries;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomConfigElement implements IConfigElement {

    private final String name;
    private final boolean isProperty;
    private Property prop;
    private ConfigCategory category;

    public CustomConfigElement(String name, Property prop) {
        this.prop = prop;
        this.isProperty = true;
        this.name = name;
    }

    public CustomConfigElement(String name, ConfigCategory category) {
        this.category = category;
        this.isProperty = false;
        this.name = name;
    }

    public static ConfigGuiType getType(Property prop) {
        return prop.getType() == Property.Type.BOOLEAN ? ConfigGuiType.BOOLEAN : prop.getType() == Property.Type.DOUBLE ? ConfigGuiType.DOUBLE :
                prop.getType() == Property.Type.INTEGER ? ConfigGuiType.INTEGER : prop.getType() == Property.Type.COLOR ? ConfigGuiType.COLOR :
                        prop.getType() == Property.Type.MOD_ID ? ConfigGuiType.MOD_ID : ConfigGuiType.STRING;
    }

    @Override
    public List<IConfigElement> getChildElements() {
        if (!isProperty) {
            List<IConfigElement> elements = new ArrayList<>();

            for (Property property : category.getOrderedValues()) {
                ConfigElement temp = new ConfigElement(property);
                if (temp.showInGui())
                    elements.add(temp);
            }
            return elements;
        }
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isProperty() {
        return isProperty;
    }

    @Override
    public Class<? extends GuiConfigEntries.IConfigEntry> getConfigEntryClass() {
        return isProperty ? prop.getConfigEntryClass() : category.getConfigEntryClass();
    }

    @Override
    public Class<? extends GuiEditArrayEntries.IArrayEntry> getArrayEntryClass() {
        return isProperty ? prop.getArrayEntryClass() : null;
    }

    @Override
    public String getQualifiedName() {
        return isProperty ? prop.getName() : category.getQualifiedName();
    }

    @Override
    public ConfigGuiType getType() {
        return isProperty ? getType(this.prop) : ConfigGuiType.CONFIG_CATEGORY;
    }

    @Override
    public boolean isList() {
        return isProperty && prop.isList();
    }

    @Override
    public boolean isListLengthFixed() {
        return isProperty && prop.isListLengthFixed();
    }

    @Override
    public int getMaxListLength() {
        return isProperty ? prop.getMaxListLength() : -1;
    }

    @Override
    public String getComment() {
        return isProperty ? prop.comment : category.getComment();
    }

    @Override
    public boolean isDefault() {
        return !isProperty || prop.isDefault();
    }

    @Override
    public void setToDefault() {
        if (isProperty)
            prop.setToDefault();
    }

    @Override
    public boolean requiresWorldRestart() {
        return isProperty ? prop.requiresWorldRestart() : category.requiresWorldRestart();
    }

    @Override
    public boolean showInGui() {
        return isProperty ? prop.showInGui() : category.showInGui();
    }

    @Override
    public boolean requiresMcRestart() {
        return isProperty ? prop.requiresMcRestart() : category.requiresMcRestart();
    }

    @Override
    public String[] getValidValues() {
        return isProperty ? prop.getValidValues() : null;
    }

    @Override
    public String getLanguageKey() {
        return isProperty ? prop.getLanguageKey() : category.getLanguagekey();
    }

    @Override
    public Object getDefault() {
        return isProperty ? prop.getDefault() : null;
    }

    @Override
    public Object[] getDefaults() {
        if (isProperty) {
            String[] aVal = prop.getDefaults();
            return getObjects(aVal);
        }
        return null;
    }

    private Object[] getObjects(String[] aVal) {
        if (prop.getType() == Property.Type.BOOLEAN) {
            Boolean[] ba = new Boolean[aVal.length];
            for (int i = 0; i < aVal.length; i++)
                ba[i] = Boolean.valueOf(aVal[i]);
            return ba;
        } else if (prop.getType() == Property.Type.DOUBLE) {
            Double[] da = new Double[aVal.length];
            for (int i = 0; i < aVal.length; i++)
                da[i] = Double.valueOf(aVal[i]);
            return da;
        } else if (prop.getType() == Property.Type.INTEGER) {
            Integer[] ia = new Integer[aVal.length];
            for (int i = 0; i < aVal.length; i++)
                ia[i] = Integer.valueOf(aVal[i]);
            return ia;
        } else
            return aVal;
    }

    @Override
    public Pattern getValidationPattern() {
        return isProperty ? prop.getValidationPattern() : null;
    }

    @Override
    public Object get() {
        return isProperty ? prop.getString() : null;
    }

    @Override
    public Object[] getList() {
        if (isProperty) {
            String[] aVal = prop.getStringList();
            return getObjects(aVal);
        }
        return null;
    }

    @Override
    public void set(Object value) {
        if (isProperty) {
            if (prop.getType() == Property.Type.BOOLEAN)
                prop.set(Boolean.parseBoolean(value.toString()));
            else if (prop.getType() == Property.Type.DOUBLE)
                prop.set(Double.parseDouble(value.toString()));
            else if (prop.getType() == Property.Type.INTEGER)
                prop.set(Integer.parseInt(value.toString()));
            else
                prop.set(value.toString());
        }
    }

    @Override
    public void set(Object[] aVal) {
        if (isProperty) {
            if (prop.getType() == Property.Type.BOOLEAN) {
                boolean[] ba = new boolean[aVal.length];
                for (int i = 0; i < aVal.length; i++)
                    ba[i] = Boolean.parseBoolean(aVal[i].toString());
                prop.set(ba);
            } else if (prop.getType() == Property.Type.DOUBLE) {
                double[] da = new double[aVal.length];
                for (int i = 0; i < aVal.length; i++)
                    da[i] = Double.parseDouble(aVal[i].toString());
                prop.set(da);
            } else if (prop.getType() == Property.Type.INTEGER) {
                int[] ia = new int[aVal.length];
                for (int i = 0; i < aVal.length; i++)
                    ia[i] = Integer.parseInt(aVal[i].toString());
                prop.set(ia);
            } else {
                String[] is = new String[aVal.length];
                for (int i = 0; i < aVal.length; i++)
                    is[i] = aVal[i].toString();
                prop.set(is);
            }
        }
    }

    @Override
    public Object getMinValue() {
        return isProperty ? prop.getMinValue() : null;
    }

    @Override
    public Object getMaxValue() {
        return isProperty ? prop.getMaxValue() : null;
    }
}