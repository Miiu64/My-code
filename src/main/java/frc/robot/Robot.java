/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
 /* private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.171, 0.438, 0.391);
  private final Color kGreenTarget = ColorMatch.makeColor(0.209, 0.532, 0.258);
  private final Color kRedTarget = ColorMatch.makeColor(0.421, 0.393, 0.183);
  private final Color kYellowTarget = ColorMatch.makeColor(0.315, 0.531, 0.152); */  //Mary's number with polycarb
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.231, 0.463, 0.305);
  private final Color kGreenTarget = ColorMatch.makeColor(0.240, 0.485, 0.274);
  private final Color kRedTarget = ColorMatch.makeColor(0.293, 0.452, 0.253);
  private final Color kYellowTarget = ColorMatch.makeColor(0.284, 0.491, 0.223); //Vikram's numbers with the polycarb



  @Override
  public void robotInit() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }
  @Override
  public void robotPeriodic() {
    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
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