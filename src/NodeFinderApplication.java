public class NodeFinderApplication {
    public static void main(String[] args) {
        Config config = new Config(
                GridSetup.GRID_WIDTH,
                GridSetup.GRID_HEIGHT,
                GridSetup.NODE_POSITIONS
        );
        AppMaster appMaster = new AppMaster(config);

        appMaster.run();

    }
}
