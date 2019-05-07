import java.util.Map;

public class CheckpointRestore {

    public static void CleanupTheWorld() {
        System.gc();
        System.gc();
    }

    private native void CheckTheWorldNative();
    private native void SaveTheWorldNative(String dir);
    private native void RestoreTheWorldNative(String dir);

    public static native void MigrateTheWorld();
    public static native void SaveTheWorldIncremental();

    public static void CheckTheWorld() {
	CheckpointRestore cr = new CheckpointRestore();
	cr.CheckTheWorldNative();
    }

    public static void SaveTheWorld(String dir) {
	CheckpointRestore cr = new CheckpointRestore();
	cr.SaveTheWorldNative(dir);
    }	

    public static void RestoreTheWorld(String dir) {
	System.out.println("RestoreTheWorld java call");
	CheckpointRestore cr = new CheckpointRestore();
	cr.RestoreTheWorldNative(dir);
    }
    
    static  {

	System.out.println("Library path = " + System.getProperty("java.library.path"));
	System.out.println("About to load Checkpoint Restore library " + System.mapLibraryName("CheckpointRestore"));
	System.out.println("About to load criu library " + System.mapLibraryName("criu"));

	System.out.println("Before call to load CheckpointRestore");
        System.loadLibrary("CheckpointRestore");
	System.out.println("After call to load CheckpointRestore");	
        System.loadLibrary("criu");
	System.out.println("After call to load criu");		
    }
}
