public class TestRandomRestore {

    public static void main(String[] args) {
	if (args.length != 1) System.out.println("Usage: please provide checkpoint directory");

	//	CheckpointRestore.CheckTheWorld();
	CheckpointRestore.RestoreTheWorld(args[0]);

	System.out.println("Done!");
    }
}
