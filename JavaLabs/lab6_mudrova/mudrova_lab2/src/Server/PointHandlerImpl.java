package Server;

import java.rmi.RemoteException;

import shared_files.Point;

public class PointHandlerImpl implements PointHandler{

	private String info;
	
	public PointHandlerImpl() {
		this.info="PointHendlerImpl";
	}
	
	@Override
	public double getIntervalLength(Point a, Point b) throws RemoteException {
		double length = Math.sqrt( Math.pow( (b.getX()-a.getX()) , 2) + Math.pow( (b.getY()-a.getY()) , 2) );
		return length;
	}

	@Override
	public double getCircleLengthByRadius(Point a, Point b) throws RemoteException {
		double length = 2 * Math.PI * getIntervalLength(a, b);
		return length;
	}

	@Override
	public double getCircleLengthByDiameter(Point a, Point b) throws RemoteException {
		double length = Math.PI * getIntervalLength(a, b);
		return length;	
		}

	@Override
	public double getCircleAreaByRadius(Point a, Point b) throws RemoteException {
		double area = Math.PI * Math.pow( getIntervalLength(a, b), 2 );
		return area;
	}

	@Override
	public double getCircleAreaByDiameter(Point a, Point b) throws RemoteException {
		double area = Math.PI * Math.pow( getIntervalLength(a, b), 2 ) / 4;
		return area;
	}

	@Override
	public String getPointsInfo() throws RemoteException {
		return "Проверка работы методов удаленного объекта - info: " + info;
		
	}

}
