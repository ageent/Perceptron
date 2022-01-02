# Perceptron

Implementation of a multi-layer perceptron (MLP) in Java with **asynchronous** fitting and predicting.

The architecture was developed taking into account further expansion - the addition of other types of neural networks.

### Implementation features

- Weights refer to the inputs of neurons (dendrites), not to their outputs (synapses) for ease of implementation.
- Currently, only stochastic gradient descent (SGD) is implemented by the backpropagation method.
  - All results direct passage are kept in memory.