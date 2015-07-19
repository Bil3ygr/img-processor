package image_processor;

public class GetColor {
	// 获取灰度值
	public int getGray(int rgb) {
        int gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299 + ((rgb & 0x0000ff00) >> 8) * 0.587 + (rgb & 0x000000ff) * 0.114);
        return gray;
    }
	// 获取R值
    public int getRed(int rgb) {
    	int red = (rgb & 0x00ff0000) >> 16;
    	return red;
    }
    // 获取G值
    public int getGreen(int rgb) {
    	int green = (rgb & 0x0000ff00) >> 8;
    	return green;
    }
    // 获取B值
    public int getBlue(int rgb) {
    	int blue = (rgb & 0x000000ff);
    	return blue;
    }
}
