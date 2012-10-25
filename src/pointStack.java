import java.util.List;
import java.util.Vector;

import toxi.geom.*;

public class pointStack extends PointOctree {
	Vector<Integer> vals = new Vector<Integer>();
	thesisWindErode p;
	int density = 10;
	int RAD = 10;
	int size;

	public pointStack(Vec3D c, float s, thesisWindErode parent) {
		super(c, s);
		p = parent;
		size = (int) s;
	}
	
	void update(){
		
		
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

	void populate(int nX, int nY, int nZ, int sX, int sY, int sZ) {
		float x = (sX * nX) / 2;
		float y = (sY * nY) / 2;

		for (int i = 0; i < nX; i++) {
			for (int j = 0; j < nY; j++) {
				for (int k = 0; k < nZ; k++) {
					this.addPoint(new Vec3D(x - (i * sX), y - (j * sY), k * sZ));
					vals.add(255);
				}
			}
		}
	}
	
	void removePt(Vec3D v){
		//get point id to remove it from a list of pts
		List<Vec3D> pts = this.getPoints();
		int ptID = 0;
		for(int i = 0; i < pts.size(); i++){
			if(pts.get(i)==v) {
				ptID = i;
				break;
			}
		}
		this.remove(v);
		vals.remove(ptID);
		vals.set(vals.size()-1, 0);
	}
	
	void drawPoints(){
		p.stroke(0);
		int i = 0;
		for(Vec3D v : this.getPoints()){
			
			int col = vals.get(i);
			p.fill(col,0,0);
			p.gfx.box(new AABB(v, RAD/2));
			i++;
		}
	}

}
