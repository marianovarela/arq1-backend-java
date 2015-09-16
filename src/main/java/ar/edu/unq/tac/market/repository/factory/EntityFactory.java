package ar.edu.unq.tac.market.repository.factory;

public abstract class EntityFactory {


    protected int getIndex(String key) {
        String schema[] = this.getSchema();
        for (int i = 0; i < schema.length; i++) {
            if (schema[i] == key) {
                return i;
            }
        }
        throw new RuntimeException("No existe el indice " + key);
    }

    protected abstract String[] getSchema();

    protected String findValue(String splitted[], String key) {
        return splitted[getIndex(key)];
    }

    protected String[] splitArrayBy(String chain, String regex) {
        String[] splitChain = chain.split(regex);
        for (int i = 0; i < splitChain.length; i++) {
            splitChain[i] = splitChain[i].trim();
        }
        return splitChain;
    }

    protected String[] splitArray(String chain) {
        return splitArrayBy(chain, ",");
    }
}
