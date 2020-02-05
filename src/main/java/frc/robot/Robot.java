package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class Robot extends TimedRobot {

  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.231, 0.463, 0.305);
  private final Color kGreenTarget = ColorMatch.makeColor(0.240, 0.485, 0.274);
  private final Color kRedTarget = ColorMatch.makeColor(0.293, 0.452, 0.253);
  private final Color kYellowTarget = ColorMatch.makeColor(0.284, 0.491, 0.223); 



  @Override
  public void robotInit() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }


  @Override
  public void robotPeriodic() {
    int proximity = m_colorSensor.getProximity();
    SmartDashboard.putNumber("Proximity", proximity);
    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    String colorString = "initialized string";
    if (proximity > 400)
    {
      if (match.color == kBlueTarget) {
        colorString = "Blue";
      } else if (match.color == kRedTarget) {
        colorString = "Red";
      } else if (match.color == kGreenTarget) {
        colorString = "Green";
      } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
      }
      else
      {
        colorString = "Unknown";
      }
      
      SmartDashboard.putNumber("Red", detectedColor.red);
      SmartDashboard.putNumber("Green", detectedColor.green);
      SmartDashboard.putNumber("Blue", detectedColor.blue);
      SmartDashboard.putNumber("Confidence", match.confidence);
      SmartDashboard.putString("Detected Color", colorString);
      SmartDashboard.putNumber("Proximity", proximity);
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
        while (true)
        {
          if (colorString.equals("Yellow"))
          {
            if (!colorString_temp.equals("Blue") || colorString_temp.equals("Green"))
            {
              while(!colorString_temp.equals("Blue") || colorString_temp.equals("Green"))
              {
                SmartDashboard.putNumber("Red", detectedColor.red);
                SmartDashboard.putNumber("Green", detectedColor.green);
                SmartDashboard.putNumber("Blue", detectedColor.blue);
                SmartDashboard.putNumber("Confidence", match.confidence);
                SmartDashboard.putString("Detected Color", "Yellow");
                SmartDashboard.putNumber("Proximity", proximity);
                detectedColor_temp = m_colorSensor.getColor();
                match_temp = m_colorMatcher.matchClosestColor(detectedColor_temp);
                if (match_temp.color == kBlueTarget) {
                  colorString_temp = "Blue";
                } else if (match_temp.color == kRedTarget) {
                  colorString_temp = "Red";
                } else if (match_temp.color == kGreenTarget) {
                  colorString_temp = "Green";
                } else if (match_temp.color == kYellowTarget) {
                  colorString_temp = "Yellow";
                }
              }
              colorString_temp = "Blue";
              SmartDashboard.putNumber("Red", detectedColor.red);
              SmartDashboard.putNumber("Green", detectedColor.green);
              SmartDashboard.putNumber("Blue", detectedColor.blue);
              SmartDashboard.putNumber("Confidence", match.confidence);
              SmartDashboard.putString("Detected Color", colorString_temp);
              SmartDashboard.putNumber("Proximity", proximity);
            }
          }
          else if (colorString.equals("Green"))
          {
            if (!colorString_temp.equals("Red") || colorString_temp.equals("Yellow"))
            {
              while(!colorString_temp.equals("Red") || colorString_temp.equals("Yellow"))
              {
                SmartDashboard.putNumber("Red", detectedColor.red);
                SmartDashboard.putNumber("Green", detectedColor.green);
                SmartDashboard.putNumber("Blue", detectedColor.blue);
                SmartDashboard.putNumber("Confidence", match.confidence);
                SmartDashboard.putString("Detected Color", "Green");
                SmartDashboard.putNumber("Proximity", proximity);
                detectedColor_temp = m_colorSensor.getColor();
                match_temp = m_colorMatcher.matchClosestColor(detectedColor_temp);
                if (match_temp.color == kBlueTarget) {
                  colorString_temp = "Blue";
                } else if (match_temp.color == kRedTarget) {
                  colorString_temp = "Red";
                } else if (match_temp.color == kGreenTarget) {
                  colorString_temp = "Green";
                } else if (match_temp.color == kYellowTarget) {
                  colorString_temp = "Yellow";
                }
              }
              colorString_temp = "Red";
              SmartDashboard.putNumber("Red", detectedColor.red);
              SmartDashboard.putNumber("Green", detectedColor.green);
              SmartDashboard.putNumber("Blue", detectedColor.blue);
              SmartDashboard.putNumber("Confidence", match.confidence);
              SmartDashboard.putString("Detected Color", colorString_temp);
              SmartDashboard.putNumber("Proximity", proximity);
            }
          }
          else if (colorString.equals("Red"))
          {
            if (!colorString_temp.equals("Yellow"))
            {
              while(!colorString_temp.equals("Yellow"))
              {
                SmartDashboard.putNumber("Red", detectedColor.red);
                SmartDashboard.putNumber("Green", detectedColor.green);
                SmartDashboard.putNumber("Blue", detectedColor.blue);
                SmartDashboard.putNumber("Confidence", match.confidence);
                SmartDashboard.putString("Detected Color", "Red");
                SmartDashboard.putNumber("Proximity", proximity);
                detectedColor_temp = m_colorSensor.getColor();
                match_temp = m_colorMatcher.matchClosestColor(detectedColor_temp);
                if (match_temp.color == kBlueTarget) {
                  colorString_temp = "Blue";
                } else if (match_temp.color == kRedTarget) {
                  colorString_temp = "Red";
                } else if (match_temp.color == kGreenTarget) {
                  colorString_temp = "Green";
                } else if (match_temp.color == kYellowTarget) {
                  colorString_temp = "Yellow";
                }
              }
              colorString_temp = "Yellow";
              SmartDashboard.putNumber("Red", detectedColor.red);
              SmartDashboard.putNumber("Green", detectedColor.green);
              SmartDashboard.putNumber("Blue", detectedColor.blue);
              SmartDashboard.putNumber("Confidence", match.confidence);
              SmartDashboard.putString("Detected Color", colorString_temp);
              SmartDashboard.putNumber("Proximity", proximity);
            }
          }
          else if (colorString.equals("Blue"))
          {
            if (!colorString_temp.equals("Green"))
            {
              while(!colorString_temp.equals("Green"))
              {
                SmartDashboard.putNumber("Red", detectedColor.red);
                SmartDashboard.putNumber("Green", detectedColor.green);
                SmartDashboard.putNumber("Blue", detectedColor.blue);
                SmartDashboard.putNumber("Confidence", match.confidence);
                SmartDashboard.putString("Detected Color", "Blue");
                SmartDashboard.putNumber("Proximity", proximity);
                detectedColor_temp = m_colorSensor.getColor();
                match_temp = m_colorMatcher.matchClosestColor(detectedColor_temp);
                if (match_temp.color == kBlueTarget) {
                  colorString_temp = "Blue";
                } else if (match_temp.color == kRedTarget) {
                  colorString_temp = "Red";
                } else if (match_temp.color == kGreenTarget) {
                  colorString_temp = "Green";
                } else if (match_temp.color == kYellowTarget) {
                  colorString_temp = "Yellow";
                }
              }
              colorString_temp = "Green";
              SmartDashboard.putNumber("Red", detectedColor.red);
              SmartDashboard.putNumber("Green", detectedColor.green);
              SmartDashboard.putNumber("Blue", detectedColor.blue);
              SmartDashboard.putNumber("Confidence", match.confidence);
              SmartDashboard.putString("Detected Color", colorString_temp);
              SmartDashboard.putNumber("Proximity", proximity);
            }
          }
          colorString = colorString_temp;
          }
      }
    }
    else
    {
      colorString = "Too Far Away";
      SmartDashboard.putNumber("Red", detectedColor.red);
      SmartDashboard.putNumber("Green", detectedColor.green);
      SmartDashboard.putNumber("Blue", detectedColor.blue);
      SmartDashboard.putNumber("Confidence", match.confidence);
      SmartDashboard.putString("Detected Color", colorString);
      SmartDashboard.putNumber("Proximity", proximity);
    }
      
  }
}