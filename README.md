# ArtificialLife

An attempt to simulate complex artificial life evolution in a controlled enviroment. 
Creatures in this enviroment do not have any hard-coded behaviour or body, and thus can evolve according to their needs and adapt to their enviroment.

## Goal

The general goal is to get a realistic looking artificial life simulation, where creatures evolve both their physical bodies and their brains (neural networks). 
The enviroment they live in should be diverse to allow different niches to be occupied and different species to appear, 
instead of a single "good-at-everything" species. To allow this, the code must only specify _how_ the bodies and the NNs can change, and leave the _what_ up to
natural selection. For this reason, a custom implementation of the Real Time NEAT algorithm will be used for the neural networks, and the bodies will be composed of basic shapes, 
and they will randomly grow new basic shapes attached in different ways in the form of mutations. The neural networks should evolve faster than the bodies, 
to allow creatures to learn how to use them, but I don't know yet how to solve that problem besides slowing down physical evolution. 
Physical mutations should also be very rare for that same reason. The energy system, like the enviroment in general, will be hard coded in the simulation,
since a naturally generated energy system would take too long to evolve and would be too complex for any machine to run properly.

There will be two separate life forms that will both evolve on their own, but will be able to interact with each other: creatures and plants.
Plant evolution will be sort of hard coded, since we want diversity but not complexity, and thus we need to control their evolution a little more strictly.
To ensure plant diversity, different areas of the map should have different constants the plants depend on. We can understand them as moisture, temperature, altitude, etc.
The only important thing is that they are somewhat constant and different across the map at all times. A large scale of different smooth noise maps could work.

#### Short term

 - Basic creatures (hard coded body structure, just neural networks with shells).
 - Basic enviroment (just random food sources, all the same).
 - Basic reproduction (instant asexual reproduction).
 - Creature saving and loading (serialization).

#### Mid term

 - Evolving body structure (based on rigid body basic shapes like triangles and circles with different kinds of joints).
 - Complex reproduction (sexual reproduction, maybe laying eggs, or any other thing, but not just instantly spawning a new creature).
 - Neural network visualization.
 - Population and simulation controls.

#### Long term

 - Population info tracking and plotting.
 - Evolving diverse enviroments (probably hard coded posibilities, we just need diversity, not complexity).
 - Variable input and output length neural networks (inspired by [Léo Caussan](https://github.com/TheNaotagrey)'s [BIOME](https://youtu.be/kNWb7e8FZDo?t=763) system in his project [The Bibites](https://www.youtube.com/channel/UCjJEUMnBFHOP2zpBc7vCnsA)).
 - Creature editing.
 - Background processing and maybe GPU acceleration.
