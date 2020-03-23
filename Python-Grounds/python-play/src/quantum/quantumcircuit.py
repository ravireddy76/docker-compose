import ClassicalRegister, QuantumRegister from qiskit
import QuantumCircuit, execute from qiskit

class Circuit:
    def __init__(circuit):
        print("Inside the constructor..")

    def computeQuantumBits(circuit):
        q = QuantumRegister(2)
        c = ClassicalRegister(2)
        qc = QuantumCircuit(q,c)
        qc.h(q[0])
        qc.cx(q[0], q[1])
        qc.measure(q, c)
        job_sim = execute(qc, "local QSM Simulator")
        sim_result = job_sim.result()
        print(sim_result.get_counts(qc))


qcircuit = Circuit()
qcircuit.computeQuantumBits()
