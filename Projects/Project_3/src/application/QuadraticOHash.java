package application;

public class QuadraticOHash<T extends Comparable<T>> extends OpenAddressignHash<T> {
	private int numberOfChoose = -1;

	public QuadraticOHash(int dataSize) {
		super(dataSize);
	}

	public int getNumberOfChoose() {
		return numberOfChoose;
	}

	public void setNumberOfChoose(int numberOfChoose) {
		this.numberOfChoose = numberOfChoose;
	}

	@Override
	public void add(T data) {
		if (getSize() == getM() / 2)
			rehash();
		int index = Math.abs(data.hashCode()) % getM();
		int i = 0;
		for (;getTable()[(index + i * (i++)) % getM()].getFlag() != Flag.EMPTY && i <= getM(); setCollisions(
						getCollisions() + 1))
			;
		if (i > getM()) // a loop occurred
			System.out.println("This element cannot be added");
		else {
			index = (index + (--i) * i) % getM();
			getTable()[index].setData(data);
			getTable()[index].setFlag(Flag.FULL);
			setSize(getSize() + 1);
		}
	}

	@Override
	public HNode<T> find(T data) {
		int index = Math.abs(data.hashCode()) % getM();
		int i = 0;
		Flag flag = getTable()[(index + i * i) % getM()].getFlag();

		for (; flag == Flag.DELETED
				|| flag == Flag.FULL && getTable()[(index + i * i) % getM()].getData().compareTo(data) != 0
						&& i <= getM(); flag = getTable()[(index + (++i) * i) % getM()].getFlag())
			;

		if (flag == Flag.FULL && getTable()[(index + i * i) % getM()].getData().compareTo(data) == 0)
			return getTable()[(index + i * i) % getM()];

		return null;
	}

	public HNode<T> getNextNode() {
		if (numberOfChoose < getTable().length - 1) {
			numberOfChoose++;
		}
		while (getTable()[numberOfChoose].getFlag() != Flag.FULL && numberOfChoose < getTable().length - 1) {
			numberOfChoose++;
		}
		if (numberOfChoose < getTable().length && getTable()[numberOfChoose].getFlag() == Flag.FULL)
			return getTable()[numberOfChoose];
		return null;
	}

	public HNode<T> getPrefNode() {
		if (numberOfChoose > 0) {
			numberOfChoose--;
		}
		while (getTable()[numberOfChoose].getFlag() != Flag.FULL && numberOfChoose > 0) {
			numberOfChoose--;
		}
		if (numberOfChoose >= 0 && getTable()[numberOfChoose].getFlag() == Flag.FULL)
			return getTable()[numberOfChoose];
		return null;
	}
	

}