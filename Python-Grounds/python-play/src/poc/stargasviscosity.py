
import matplotlib.pylab as plt


class GasViscosityCal:
    def __init__(gasViscosityCal, constant, temperature, muData):
        gasViscosityCal.constant = constant
        gasViscosityCal.temperature = temperature
        gasViscosityCal.muData = muData



    def poltResults(gasViscosityCal):
        resultData = gasViscosityCal.__calculateGasViscosity()
        # print(resultData)
        
        # Give the marker symbols for each gases to show as label markers in the plotted graph.
        gasMarkers = ['d', '^', 'v', 's', '+', '<', 'x', '.']
        gasMarkerCounter = 0

        # Plot the legend or label with markers for each gas.
        for gasKey in resultData:
            plt.plot([],[], gasMarkers[gasMarkerCounter], label=gasKey)
            gasMarkerCounter = gasMarkerCounter+1

        # Plot the calculated values for given temperature for each gas.
        for gasKey in resultData:
            gasData = resultData[gasKey]
            for gasDataKey in gasData:
                plt.scatter(int(gasDataKey), gasData[gasDataKey])

            # Animate each gas display in graph by delaying.
            plt.pause(0.05)
 
        # Set graph properties.
        plt.legend(numpoints=1)
        plt.title('Gases Viscosity')
        plt.xlabel('Temperature')
        plt.ylabel('Calculated Viscosity')
        plt.axis([0, 1000, 0.5, 14])        
        plt.show()


    # Function to calculate gas viscosity.
    def __calculateGasViscosity(gasViscosityCal):
        inputDataValues = gasViscosityCal.__readFileContent()
        computedData = {}
        # computedCalculatedValues = {}

        # Giving temperature ranges to formula to calculate gas viscocity
        for key in inputDataValues:
            #Initialize the computed calculated values map.
            computedCalculatedValues = {}
            for temperature in range(100, 1000,100):
                # Formula :: mu_0*(T_0 + C)/(T + C)(T/T_0)**1.5 to compute for given input values
                mu0Value = float(inputDataValues[key][2])
                t0Value = float(inputDataValues[key][1])
                cValue = float(inputDataValues[key][0])               
                
                mu = mu0Value*(t0Value + cValue)/((temperature + cValue)*(temperature/t0Value))**1.5 
                computedCalculatedValues[str(temperature)] = mu

            #Collecting all calculated results for given temperature in a hashmap or directories
            computedData[key] = computedCalculatedValues
  
        return computedData


    # Function or method to read file and make data map.
    def __readFileContent(gasViscosityCal):
        file = open("/Users/rredd16/Ravi-POCs/Python-Grounds/python-play/src/poc/gas-viscosity.txt", "r")
        dataMap = {}
        file.readline()
        for iter in file:
            record = iter.rstrip('\r\n').split(',')
            for data in record:
                dataMap[record[0]] = [record[1],record[2],record[3]]

        return dataMap
        

gasViscosity = GasViscosityCal(7,2,3)
gasViscosity.poltResults()

