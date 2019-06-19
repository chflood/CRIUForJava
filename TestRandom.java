
class BeforeHook extends Hook {
    String str;
    BeforeHook(String s) {
	str = s;
    }
	
    public void run() {
	System.out.println("Hook:Before Checkpoint: " + str);
    }
}

class AfterHook extends Hook {
    String str;
    AfterHook(String s) {
	str = s;
    }

    public void run() {
	System.out.println("Hook:After Restore: " + str);
    }
}


public class TestRandom {
    
    public static void  main(String[] args) {
        if (args.length != 2) System.out.println("Usage: please provide upper bound and sample size");
        int upperBound = Integer.parseInt(args[0]);        
        long sampleSize = Long.parseLong(args[1]);
        long testarray[] = new long[upperBound];

        for (long i = 0; i < sampleSize; i++) {
            Double r = Math.random() * upperBound;
            int v = (int)  Math.floor(r);
            testarray[v] += 1;
        }
	//	CheckpointRestore.CheckTheWorld();
	long end = System.currentTimeMillis();
	String dir = "/home/chf/SavedWorlds/run" + end;
	
	CheckpointRestore.RegisterCheckpointHook(new BeforeHook("Restore Command: As root java -XX:+UseSerialGC -XX:-UsePerfData -Djava.library.path=$LOAD_LIBRARY_PATH  TestRandomRestore " + dir ));
	CheckpointRestore.RegisterRestoreHook(new AfterHook("That's all folks"));	
	CheckpointRestore.SaveTheWorld(dir);
	
        long max = 0; long min = upperBound; long average = 0;
        for (int i = 0; i < upperBound; i++) {
            long s = testarray[i];
            if (s > max) { max = s;}
            if (s < min) { min = s;}
            average = average + s;
        }
        double median = average / upperBound;

        System.out.println("Testing random number generator with upper bound = " + upperBound + " and sample size " + sampleSize);
        System.out.println("Max bin size = " + max + " Min bin size = " + min + " median bin size = " + median);
    }
}
