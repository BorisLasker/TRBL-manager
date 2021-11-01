package Logic;

public class Multi implements Runnable {
	// to stop the thread
    private boolean exit;
  
    private String name;
    private Thread t;
  
    public Multi(Thread t,String threadname)
    {
        name = threadname;
       // t = new Thread(this, name);
       // System.out.println("New thread: " + t);
    	this.t=t;
        exit = false;
        
       
    }
  
    // execution of thread starts from run() method
    public void run()
    {
    	System.out.println(name+" runs");
    	t.start();
        while (!exit) {
        	try {
        		t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                
        }
       
    }
  
    public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	// for stopping the thread
    public void stop()
    {
    	System.out.println(name+" stops");
    	
        exit = true;
    }
}

