import toxi.geom.PointOctree;
import toxi.geom.Vec3D;

public class visibleOctree extends PointOctree {
	thesisWindErode p;

	public visibleOctree(Vec3D c, float size, thesisWindErode parent) {
		super(c, size);
		p = parent;
	}

	void draw() {
		drawNode(this);
	}

	void drawNode(PointOctree n) {
		if (n.getNumChildren() > 0) {
			p.noFill();
			p.stroke(n.getDepth(), 20);
			p.pushMatrix();
			p.translate(n.x, n.y, n.z);
			p.box(n.getNodeSize());
			p.popMatrix();
			PointOctree[] childNodes = n.getChildren();
			for (int i = 0; i < 8; i++) {
				if (childNodes[i] != null)
					drawNode(childNodes[i]);
			}
		}
	}

}
