public class Planet{

	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img; 
	}

	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b){
		double xxDis = Math.abs(this.xxPos - b.xxPos);
		double yyDis = Math.abs(this.yyPos - b.yyPos);
		return Math.sqrt(Math.pow(xxDis,2) + Math.pow(yyDis,2));
	}

	public double calcForceExertedBy(Planet b){
		double r = this.calcDistance(b);
		return G*this.mass*b.mass/Math.pow(r,2);
	}

	public double calcNetForceExertedByX(Planet[] bodys){
		double sum = 0;
		for(Planet b : bodys){
			if(this.equals(b)) continue;
			double xDis = b.xxPos - this.xxPos;
			double r = this.calcDistance(b);
			sum = sum + this.calcForceExertedBy(b)*xDis/r;
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] bodys){
		double sum = 0;
		for(Planet b : bodys){
			if(this.equals(b)) continue;
			double yDis = b.yyPos - this.yyPos;
			double r = this.calcDistance(b);
			sum = sum + this.calcForceExertedBy(b)*yDis/r;
		}
		return sum;
	}

	public void update(double dt, double fX, double fY){
		double a_neX = fX/this.mass;
		double a_neY = fY/this.mass;
		this.xxVel = this.xxVel + dt * a_neX;
		this.yyVel = this.yyVel + dt * a_neY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public static void draw(Planet[] bodies){
		for(Planet b : bodies){
			String imageToDraw = "images/" + b.imgFileName;
			StdDraw.picture(b.xxPos, b.yyPos, imageToDraw);
		}
	}
	
}