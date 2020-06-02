import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	int hp;
	boolean isActive = true;
	Rectangle collisionBox;
	GameObject(int x, int y, int width, int height, int hp){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hp = hp;
		collisionBox = new Rectangle(x, y, width, height);
	}
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
}
