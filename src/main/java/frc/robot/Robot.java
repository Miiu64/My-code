package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class Robot extends TimedRobot {

  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.231, 0.463, 0.305);
  private final Color kGreenTarget = ColorMatch.makeColor(0.240, 0.485, 0.274);
  private final Color kRedTarget = ColorMatch.makeColor(0.293, 0.452, 0.253);
  private final Color kYellowTarget = ColorMatch.makeColor(0.284, 0.491, 0.223); 
  private String theColor;
  private WPI_TalonSRX frictionWheel = new WPI_TalonSRX(6);



  @Override
  public void robotInit() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }
  
  @Override
  public void robotPeriodic() 
  {
    
  }


  public void getColor() 
  {
    int proximity = m_colorSensor.getProximity();
    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    String colorString = "initialized colorstring";
    if (proximity > 190)
    {
      if (match.color == kBlueTarget) 
      {
        colorString = "Blue";
      } else if (match.color == kRedTarget) 
      {
        colorString = "Red";
      } else if (match.color == kGreenTarget) 
      {
        colorString = "Green";
      } else if (match.color == kYellowTarget) 
      {
        colorString = "Yellow";
      }
      else
      {
        colorString = "Unknown";
      }
      
      theColor = colorString;

      for (int i = 999; i > 0; i--)
      {
        
      }
      Color detectedColor_temp = m_colorSensor.getColor();
      ColorMatchResult match_temp = m_colorMatcher.matchClosestColor(detectedColor_temp);
      String colorString_temp = "initialized coloring temp";
      if (match_temp.color == kBlueTarget) {
        colorString_temp = "Blue";
      } else if (match_temp.color == kRedTarget) {
        colorString_temp = "Red";
      } else if (match_temp.color == kGreenTarget) {
        colorString_temp = "Green";
      } else if (match_temp.color == kYellowTarget) {
        colorString_temp = "Yellow";
      }


      if (!colorString_temp.equals(colorString))
      {

          if (colorString.equals("Yellow"))
          {
            if (colorString_temp.equals("Blue") && !colorString_temp.equals("Green"))
            {
                theColor = "Blue";
            }
          }
          else if (colorString.equals("Green"))
          {
            if (colorString_temp.equals("Red") && !colorString_temp.equals("Yellow"))
            {
              theColor = "Red";
            }
          }
          else if (colorString.equals("Red"))
          {
            if (colorString_temp.equals("Yellow"))
            {
              theColor = "Yellow";
            }
          }
          else if (colorString.equals("Blue"))
          {
            if (colorString_temp.equals("Green"))
            {
                theColor = "Green";
            }
          }
      }
    }
    else
    {
      theColor = "Too Far Away";
    }
    SmartDashboard.putString("Detected Color", theColor);
}

@Override
public void teleopInit(){
}
@Override
public void teleopPeriodic()
{
  getColor();
  PositionControl();
}
public void PositionControl()
{
  String destColor = DriverStation.getInstance().getGameSpecificMessage();

  if (destColor.equals("Y"))
  {
    destColor = "Green";
  }
  else if (destColor.equals("B"))
  {
    destColor = "Red";
  }
  else if (destColor.equals("G"))
  {
    destColor = "Yellow";
  }
  else if (destColor.equals("R"))
  {
    destColor = "Blue";
  }
  if (!theColor.equals(destColor))
  {
    frictionWheel.set(0.3);  //0.3 is too fast because of intertia, 0.1 is too slow
  }                          //Number being set on friction wheel
  else                       //Test with Joystick
  {
    frictionWheel.setNeutralMode(NeutralMode.Brake);
    frictionWheel.set(0.0);
    frictionWheel.stopMotor();
    frictionWheel.setNeutralMode(NeutralMode.Brake);
    frictionWheel.set(0.0);
    frictionWheel.stopMotor();
   /* frictionWheel.set(0.1);
    frictionWheel.setNeutralMode(NeutralMode.Brake);
    frictionWheel.set(0.0); */
  }
}
public void Camera()
{
  CameraServer.getInstance().startAutomaticCapture();
} 
}
  
