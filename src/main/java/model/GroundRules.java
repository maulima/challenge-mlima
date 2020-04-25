package model;

public class GroundRules {
    public Integer totalShotsPerFrame;
    public Integer totalAvailablePin;
    public Integer totalFramesRegularMoves;
    public Integer totalFrames;

    public Integer getTotalShotsPerFrame() {
        return totalShotsPerFrame;
    }

    public Integer getTotalAvailablePin() {
        return totalAvailablePin;
    }

    public Integer getTotalFramesRegularMoves() {
        return totalFramesRegularMoves;
    }

    public Integer getTotalFrames() {
        return totalFrames;
    }

    public GroundRules() {
        this.totalShotsPerFrame = 2;
        this.totalAvailablePin = 10;
        this.totalFramesRegularMoves = 9;
        this.totalFrames = 10;
    }
}
