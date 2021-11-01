package Logic;

import javafx.scene.control.Button;

public class Tableinfo {

	private int num_process;
	private String name_process;
	private String Cancel1;
	private String wanted_lvl;
	private Button button;
	private Multi task;

	public Tableinfo(int index_process, String name_process, String wanted_lvl, String Cancel1, Button button) {
		this.num_process = index_process;
		this.name_process = name_process;
		this.Cancel1 = Cancel1;
		this.wanted_lvl = wanted_lvl;
		this.button = button;
		this.button.setText("X");
	}

	public Multi getTask() {
		return task;
	}

	public void setTask(Multi task) {
		this.task = task;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public int getNum_process() {
		return num_process;
	}

	public void setNum_process(int num_process) {
		this.num_process = num_process;
	}

	public String getName_process() {
		return name_process;
	}

	public void setName_process(String name_process) {
		this.name_process = name_process;
	}

	public String getCancel1() {
		return Cancel1;
	}

	public void setCancel1(String cancel1) {
		Cancel1 = cancel1;
	}

	public String getWanted_lvl() {
		return wanted_lvl;
	}

	public void setWanted_lvl(String wanted_lvl) {
		this.wanted_lvl = wanted_lvl;
	}

	@Override
	public boolean equals(Object other) {
		Tableinfo c = (Tableinfo) other;
		if (c.name_process.equals(this.name_process) && c.wanted_lvl.equals(this.wanted_lvl))
			return true;

		return false;

	}

}
